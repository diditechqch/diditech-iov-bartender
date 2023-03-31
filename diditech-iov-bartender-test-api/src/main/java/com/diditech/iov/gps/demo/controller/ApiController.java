package com.diditech.iov.gps.demo.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import com.alibaba.fastjson.JSONObject;
import com.diditech.iov.gps.common.domain.AdminRegion;
import com.diditech.iov.gps.common.domain.CustomerChurnPoint;
import dd.utils.Coordinate;
import dd.utils.GISFixUtil;
import dd.utils.Util;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 测试api
 * @author hefan
 * @date 2020/8/18 10:26
 */
@Slf4j
@RestController
@RequestMapping("/api-test")
public class ApiController {

    @Autowired
    private MongoTemplate mongoTemplate;


    @GetMapping("/hello")
    public String getRequest(HttpServletRequest request) {
        return "Hello World." + request.getHeader("client");
    }

    @GetMapping("/no-auth")
    public String noAuth() {
        return "noAuth.";
    }

    @GetMapping("/regions")
    public List<JSONObject> query(@RequestParam(value = "regionName", required = false) String regionName,
                                  @RequestParam(value = "bdLat") Double bdLat,
                                  @RequestParam(value = "bdLng") Double bdLng) {
        GeoJsonPoint point = new GeoJsonPoint(bdLng, bdLat);

        Criteria criteria = Criteria.where("points").intersects(point);
        if (!Util.isEmpty(regionName)) {
            Pattern pattern = Pattern.compile("^.*" + regionName + ".*$");
            criteria.and("regionName").regex(pattern);
        }

        Query query = new Query();
        query.addCriteria(criteria);
        query.fields().exclude("_id").include("regionName").include("regionLevel").include("directGoverned");
//        query.fields().exclude("_id").exclude("points"); // 效果相同
        return mongoTemplate.find(query, JSONObject.class, "regions");
    }

    @GetMapping("/regions/{regionName}")
    public List<String> getPoints(@PathVariable(value = "regionName") String regionName) {
        Pattern pattern = Pattern.compile("^.*" + regionName + ".*$");
        Query query = new Query();
        query.addCriteria(Criteria.where("regionName").regex(pattern));
        List<AdminRegion> list = mongoTemplate.find(query, AdminRegion.class, "regions");
        if (CollUtil.isEmpty(list)) {
            return null;
        }

        if (regionName.contains("北京") || regionName.contains("天津")
                || regionName.contains("重庆") || regionName.contains("上海")
                || regionName.contains("香港") || regionName.contains("澳门")
                || regionName.contains("台湾")) {
            list = list.stream().filter(item -> item.getRegionLevel() == 2).collect(Collectors.toList());
        }

        List<String> points = new ArrayList<>();
        String pointsString;
        for (AdminRegion item : list) {
            pointsString = item.getPoints().getCoordinates().get(0)
                    .getCoordinates().stream()
                    .map(p -> Convert.toStr(p.getX()).concat(",").concat(Convert.toStr(p.getY())).concat(";"))
                    .collect(Collectors.joining());
            points.add(pointsString.substring(0, pointsString.length() - 1));
        }
        return points;
    }


    @PostMapping("/regions")
    public String getDistinction(@RequestParam(value = "level") Integer level,
                                 @RequestParam(value = "name") String name,
                                 @RequestBody String points) {
        points = points
                .trim()
                .replace("\"", "")
                .replace(";", "],[");
        points = "{\"type\":\"Polygon\", \"coordinates\":[[[" + points + "]]]}";
        GEOPolygon geoPolygon = JSONObject.parseObject(points, GEOPolygon.class);
        try {
            saveDistinction(level, name, geoPolygon);
        } catch (Exception ex) {
            log.error("插入失败：" + name + "," + points, ex);
        }
        return "";
    }

    public void saveDistinction(int level, String name, GEOPolygon geoPolygon) {
        List<Point> points = new ArrayList<>();
        List<List<List<Double>>> coordinates = geoPolygon.getCoordinates();
        List<List<Double>> coordinate = coordinates.get(0);
        for (List<Double> doubles : coordinate) {
            Coordinate baseCoordinate = new Coordinate(doubles.get(1), doubles.get(0)); // new Coordinate(lat,lng)
            Coordinate bdCoord = GISFixUtil.convertGCJ02ToBD09(baseCoordinate);
            Point point = new Point(bdCoord.lng, bdCoord.lat);
//            Point point = new Point(doubles.get(0), doubles.get(1));
            points.add(point);
        }
        Point lastPoint = points.get(points.size() - 1);
        Point firstPoint = points.get(0);

        if (!lastPoint.equals(firstPoint)) {
            points.add(new Point(firstPoint));
        }
        String array = points.stream()
                .map(item -> Util.asString(item.getX()).concat(",").concat(Util.asString(item.getY())))
                .collect(Collectors.joining("],["));
        array = "[[" + array + "]]";
        log.debug("name={}, array={}", name, array);
        String bdformat = points.stream()
                .map(item -> Util.asString(item.getX()).concat(",").concat(Util.asString(item.getY())))
                .collect(Collectors.joining(";"));
        log.debug("name={}, bdformat={}", name, bdformat);
        AdminRegion d = new AdminRegion(level, name, new GeoJsonPolygon(points));
        CompletableFuture.runAsync(() -> mongoTemplate.insert(d, "regions"));
    }

    @Data
    public static class GEOPolygon {
        String type;
        List<List<List<Double>>> coordinates;
    }


    @GetMapping("/ccp")
    public void getDistinction() {
        CustomerChurnPoint ccp = new CustomerChurnPoint();
        ccp.setAddress("伊犁哈萨克自治州 山东路与福州路交叉口东南100米");
        ccp.setCreateTime(new Date());
        ccp.setCustomerId(2342342);
        ccp.setIsDel(0);
        ccp.setIsEnable(1);
        ccp.setLocBd(new GeoJsonPoint(124.34543, 24.3243));
        ccp.setName("ddddd");
        mongoTemplate.insert(ccp, "customer_churn_points");
    }


}
