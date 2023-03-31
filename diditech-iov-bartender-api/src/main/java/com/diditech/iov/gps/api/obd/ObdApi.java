package com.diditech.iov.gps.api.obd;

import com.diditech.iov.gps.api.core.ResponseMessage;
import com.diditech.iov.gps.api.obd.domain.ObdData;
import com.diditech.iov.gps.api.obd.domain.ObdFault;
import com.diditech.iov.gps.api.obd.domain.ObdFuel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

/**
 * 设备信息查询接口
 * @author zhaist
 * @date 2020-11-18
 */
@RequestMapping("/obd")
public interface ObdApi {

    /**
     * 按设备编号查询OBD故障码信息
     * @param deviceNum    设备编号
     * @param vehicleBrand 品牌车系(非必输)
     * @return 返回对象 {@link ResponseMessage ResponseMessage.getData()}值为 {@link ObdFault com.diditech.iov.gps.api.obd.domain.ObdFault } 查询OBD故障信息
     **/
    @RequestMapping(value = "/obd-fault", method = RequestMethod.GET)
    ResponseMessage getObdFaultList(@RequestParam(value = "deviceNum") String deviceNum,
                                    @RequestParam(value = "vehicleBrand", required = false) String vehicleBrand);

    /**
     * 查询设备OBD行车数据
     * @param deviceNum 设备编号
     * @return 返回对象 {@link ResponseMessage ResponseMessage.getData()}值为 {@link  ObdData com.diditech.iov.gps.api.obd.domain.ObdData } 查询OBD行车数据。
     **/
    @RequestMapping(value = "/obd-data", method = RequestMethod.GET)
    ResponseMessage getObdData(@RequestParam(value = "deviceNum") String deviceNum);

    /**
     * 查询时间范围的油耗
     * @param deviceNum 设备编号
     * @param startTime 开始时间 兼容多种格式
     * @param endTime   结束时间 兼容多种格式
     * @return 返回对象 {@link ResponseMessage ResponseMessage.getData()}值为 {@link  ObdFuel com.diditech.iov.gps.api.obd.domain.ObdFuel } 查询时间范围的油耗。
     **/
    @RequestMapping(value = "/obd-fuel", method = RequestMethod.GET)
    ResponseMessage getObdFuel(@RequestParam(value = "deviceNum") String deviceNum,
                               @RequestParam(value = "startTime") Date startTime,
                               @RequestParam(value = "endTime") Date endTime);

    /**
     * 查询单设备多个时间范围的油耗
     * @param deviceNum  设备号
     * @param timeFrames 时间范围 使用yyyy-MM-dd HH:mm:ss格式表示日期，一组时间范围包含两个日期，逗号间隔，多组时间范围使用竖线分隔，
     *                   例如，timeFrames=2022-01-09 15:06:32,2022-01-09 15:17:22|2022-01-09 15:33:26,2022-01-09 17:04:40|2022-01-09 17:52:28,2022-01-09 18:51:13
     * @return 返回对象 {@link ResponseMessage ResponseMessage.getData()}值为 返回结果为Integer数组，数量与请求的时间范围数量相同，没有null值
     * @date 2022/1/12
     * @author zhjd
     */
    @RequestMapping(value = "/obd-fuel/batch", method = RequestMethod.GET)
    ResponseMessage getObdFuelMulti(@RequestParam(value = "deviceNum") String deviceNum,
                                    @RequestParam(value = "timeFrames") String timeFrames);
}
