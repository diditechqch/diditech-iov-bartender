package com.diditech.iov.gps.demo.apache.rocketmq;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author zhjd <br>
 * @date 2022/4/21 <br>
 */
@Slf4j
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class OrderedConsumerTest {

    @Autowired
    private OrderedProducerService orderedProducerService;

    @Test
    public void test() {
        orderedProducerService.sendTestMessage();
    }

}