package com.diditech.iov.gps.demo.apache.rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * @author zhjd <br>
 * @date 2022/4/19 <br>
 */
public class ScheduledMessageConsumer {
    public static void main(String[] args) throws Exception {
        // Instantiate message consumer
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("example_group_name");
        consumer.setNamesrvAddr("121.42.249.58:9876");
        // Subscribe topics
        consumer.subscribe("TopicTest", "*");
        // Register message listener
        consumer.registerMessageListener((MessageListenerConcurrently) (messages, context) -> {
            for (MessageExt message : messages) {
                System.out.println(String.format("Receive message msgId=%s, body=%s, delay=%sms", message.getMsgId(),
                        new String(message.getBody()), System.currentTimeMillis() - message.getStoreTimestamp()));
                // Print approximate delay time period
            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        // Launch consumer
        consumer.start();
    }
}
