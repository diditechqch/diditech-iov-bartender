package com.diditech.iov.gps.app.geo.address.service.impl;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.diditech.iov.gps.api.trace.entity.CoordinateType;
import com.diditech.iov.gps.app.core.util.Const;
import com.diditech.iov.gps.app.geo.address.baidu.vo.GeoHash;
import com.diditech.iov.gps.app.geo.address.config.GeoAddressConfig;
import com.diditech.iov.gps.app.geo.address.repository.GeoMapper;
import com.diditech.iov.gps.app.geo.address.service.BaiduServiceI;
import com.diditech.iov.gps.app.geo.address.service.GeoServiceI;
import com.diditech.utils.Coordinate;
import com.diditech.utils.GISFixUtil;
import com.spatial4j.core.io.GeohashUtils;
import dd.utils.GPSUtil;
import dd.utils.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

/**
 * 反地理编码服务实体类
 * @author zhjd <br>
 * @date 2022/3/2 <br>
 */
@Slf4j
@Service
public class GeoService implements GeoServiceI {

    @Autowired
    private BaiduServiceI baiduService;

    @Autowired
    private GeoAddressConfig geoConfig;

    @Autowired
    private GeoMapper mapper;

    @Autowired
    @Qualifier("geoExecutor")
    private ExecutorService executor;

    @Override
    public String geo(String lat, String lng, String coorType) {
        if (!GPSUtil.isValidLatLng(lat, lng)) {
            log.error("invalidate parameters: lat ({}) lng({})", lat, lng);
            return Const.EMPTY;
        }
        try {
            Coordinate coordinate = convertToGCJ02(lat, lng, coorType);
            return getAddress(coordinate.lat, coordinate.lng);
        } catch (Exception ex) {
            log.error("geo error", ex);
        }
        return Const.EMPTY;
    }

    @Override
    public String geoBatch(String reqStr, String coorType) {
        String[] coorArray = reqStr.split(Const.REGEX_LINE);
        if (ArrayUtil.isEmpty(coorArray)) {
            return Const.EMPTY;
        }

        Map<String, String> result = runParallelGeo(coorType,
                Arrays.stream(coorArray)
                        .distinct()
                        .collect(Collectors.toList()));

        List<String> addressList = Arrays.stream(coorArray)
                .map(item -> result.get(item))
                .collect(Collectors.toList());

        return String.join(Const.SEP_COMMA, addressList);
    }

    @Override
    public List<String> geoBatchReturnList(String reqStr, String coorType) {
        if (Util.isEmpty(reqStr)) {
            return null;
        }
        String addressResult = geoBatch(reqStr, coorType);
        return Arrays.asList(addressResult.split(StrUtil.COMMA));
    }

    private String getAddress(double lat, double lng) {
        String address;
        String geoCode8 = GeohashUtils.encodeLatLon(lat, lng, GEO_HASH_CODE_8);

        if (geoConfig.isUseLocalDb()) {
            address = loadAddressFromLocal(geoCode8);
            if (StrUtil.isNotBlank(address) && !address.contains("未解析成功城市")) {
                return trimAddress(address);
            }
        }

        address = baiduService.loadAddress(lat, lng);

        if (geoConfig.isUseLocalDb() && StrUtil.isNotBlank(address)) {
            saveGeoData(lat, lng, geoCode8, address);
        }

        return trimAddress(address);
    }

    /**
     * 查询本地地理库
     * @author zhjd
     */
    private String loadAddressFromLocal(String geoCode8) {
        final List<GeoHash> addressList = mapper.searchGeoLib(geoCode8);
        return baiduService.crash(addressList, () -> mapper.deleteData(geoCode8));
    }

    private void saveGeoData(double lat, double lng, String geoCode8, String address) {
        CompletableFuture.runAsync(() ->
                mapper.insertData(new GeoHash(lng, lat, geoCode8, address,
                        geoConfig.getSource(), ADDRESS_TYPE_BAIDU, geoConfig.getVersion(), new Date()))
        );
    }

    /**
     * 去除地址字符串中特殊字符
     * @author zhjd
     */
    private String trimAddress(String address) {
        return address.replaceAll(Const.SEP_COMMA, Const.SPACE) // 含有逗号，替换为空格
                .replaceAll(Const.REGEX_TAB, Const.EMPTY) // 含有tab，替换为空字符串
                .replaceAll(Const.REGEX_NEWLINE, Const.EMPTY) // 含有换行，替换为空字符串
                .replaceAll(Const.REGEX_RETURN, Const.EMPTY); // 含有return，替换为空字符串
    }

    private Coordinate convertToGCJ02(String lat, String lng, String fromCoorType) {
        Coordinate c = new Coordinate(lat, lng);
        if (CoordinateType.get(fromCoorType) == CoordinateType.GCJ02) {
            return c;
        }
        if (CoordinateType.get(fromCoorType) == CoordinateType.BD09) {
            return GISFixUtil.convertBD09ToGCJ02(c);
        }
        return GISFixUtil.fixCoordinate(c);
    }

    /**
     * 并行线程执行反地理编码
     * @author zhjd
     */
    private Map<String, String> runParallelGeo(String coorType, List<String> coorList) {
        Map<String, String> resultMap = new ConcurrentHashMap<>();
        List<CompletableFuture<Void>> tasks = new ArrayList<>();

        coorList.forEach(item -> tasks.add(
                CompletableFuture.runAsync(() -> {
                    String[] coordinate = item.split(Const.SEP_COMMA);
                    String addr = geo(coordinate[0], coordinate[1], coorType);
                    resultMap.put(item, addr);
                }, executor)));

        tasks.stream().map(CompletableFuture::join).count();
        return resultMap;
    }
}
