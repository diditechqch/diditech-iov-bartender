package com.diditech.iov.gps.demo.apache.rocketmq;

import com.alibaba.fastjson.JSON;
import dd.utils.Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * @author zhjd <br>
 * @date 2022/4/19 <br>
 */
@Slf4j
@Service
public class OrderedProducerService {

    @Autowired
    private DefaultMQProducer producer;

    public static int delayTime_7sec = 7; // level1=1s
    public static int delayTime_11min = 5; // level5=1m


    public void sendTestMessage() {
        BizMessage bizMessage;
        for (int i = 0; i < 10; i++) {
            int orderId = i % 10;
            bizMessage = new BizMessage(Util.asString(i), 1, delayTime_7sec, 0, new Date());
            send(bizMessage, 1, 1);
        }
        for (int i = 100; i < 110; i++) {
            int orderId = i % 10;
            bizMessage = new BizMessage(Util.asString(i), 1, 0, delayTime_11min, new Date());
            send(bizMessage, 5, 2);
        }
    }

    public void send(Object msgBody, int delayTimeLevel, int queueId) {
        Message msg = new Message();
        msg.setTopic("Ordered-Topic");
        msg.setTags("Ordered-Tag");
        try {
            msg.setBody(JSON.toJSONString(msgBody).getBytes(RemotingHelper.DEFAULT_CHARSET));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        msg.setDelayTimeLevel(delayTimeLevel);
        SendResult sendResult = null;
        try {
            sendResult = producer.send(msg, (mqs, msg1, arg) -> mqs.get(queueId % mqs.size()), queueId);
        } catch (MQClientException | RemotingException | MQBrokerException | InterruptedException e) {
            e.printStackTrace();
        }
        log.info("Produce message msgId={}, body={}, sendResult={}", sendResult.getMsgId(), new String(msg.getBody()), sendResult);
    }

    public Message buildMessage(String msgBody, int delayTimeLevel) {
        Message msg = new Message();
        msg.setTopic("Ordered-Topic");
        msg.setTags("Ordered-Tag");
        try {
            msg.setBody(msgBody.getBytes(RemotingHelper.DEFAULT_CHARSET));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        msg.setDelayTimeLevel(delayTimeLevel);
        return msg;
    }
}
