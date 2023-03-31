package com.diditech.iov.gps.common.domain;

import lombok.Data;

/**
 * 推送对象
 * @author hefan
 * @date 2020/6/24 14:32
 */
@Data
public class EventData {

    /**
     * rocketmq topic
     */
    private String topic;

    /**
     * rocketmq tag
     */
    private String tag;

    /**
     * 事件类型参看{@link EventType}
     */
    private int eventType;

    /**
     * 事件详情{@link DeviceMessage}的json字符串
     */
    private String jsonData;

}
