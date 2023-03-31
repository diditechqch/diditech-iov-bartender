package com.diditech.iov.gps.demo.apache.rocketmq;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.stream.Collectors;

import static com.alibaba.fastjson.JSON.parseObject;

/**
 * @author zhjd <br>
 * @date 2022/4/19 <br>
 */
@Slf4j
@Service
public class OrderedConsumerService {

    @Autowired
    private DefaultMQPushConsumer consumer;

    @Autowired
    private OrderedProducerService producerService;

    public void start() {
        try {
            consumer.subscribe("Ordered-Topic", "Ordered-Tag");
        } catch (MQClientException e) {
            e.printStackTrace();
        }
        consumer.registerMessageListener((MessageListenerOrderly) (msgs, context) -> {
            log.info("msg={}", msgs.stream().map(item -> item + "," + new String(item.getBody())).collect(Collectors.joining("/r/n")));
            for (MessageExt msg : msgs) {
                BizMessage devMsg;
                try {
                    devMsg = parseObject(new String(msg.getBody()), BizMessage.class);
                } catch (Exception ex) {
                    return ConsumeOrderlyStatus.SUCCESS;
                }
                if (devMsg == null) {
                    return ConsumeOrderlyStatus.SUCCESS;
                }
                if (devMsg.getDelayFlag() == 1) {
                    if (devMsg.getDelayTimeInSec() > 0 &&
                            devMsg.getDelayTimeInSec() > DateUtil.between(devMsg.getCreateTime(), new Date(), DateUnit.SECOND)) {
                        producerService.send(devMsg, 1, 1);
                        continue;
                    }
                    if (devMsg.getDelayTimeInMin() > 0 &&
                            devMsg.getDelayTimeInMin() > DateUtil.between(devMsg.getCreateTime(), new Date(), DateUnit.MINUTE)) {
                        producerService.send(devMsg, 5, 2);
                        continue;
                    }
                }
                log.info("Receive message msgId={}, body={}", msg.getMsgId(), JSON.toJSONString(devMsg));
            }
            return ConsumeOrderlyStatus.SUCCESS;

        });
        try {
            consumer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
        log.info("Consumer Started.");
    }
}
