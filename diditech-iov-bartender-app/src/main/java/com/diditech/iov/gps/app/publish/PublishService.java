package com.diditech.iov.gps.app.publish;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 发布跨平台业务消息
 * @author zhjd <br>
 * @date 2021/8/12 <br>
 */
@Slf4j
@Service
public class PublishService {

    public void publishTcpMsg(String host, int port, String msg) {
        TcpPublisher tcpPublisher = new TcpPublisher(host, port);
        try {
            tcpPublisher.startWriteAndFlush(msg);
        } catch (Exception e) {
            log.error("", e);
        }
    }
}
