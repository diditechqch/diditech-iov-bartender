package com.diditech.iov.gps.app.subscribe;

import com.diditech.iov.gps.api.cmd.domain.R;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * TCP 订阅服务
 * @author zhjd <br>
 * @date 2021/7/28 <br>
 */
@Slf4j
@Configuration
public class TcpSubscriber {

    @Value("${subscribe.listen.port}")
    private int port;

    /**
     * 订阅服务接收信封
     */
    @Bean("TcpSubscriberEnvelope")
    public Cache<String, List<R>> envelope() {
        return CacheBuilder.newBuilder().expireAfterWrite(10, TimeUnit.MINUTES).build();
    }

    @Bean
    public ServerBootstrap getTcpSubscriber() throws InterruptedException {
        ServerBootstrap tcpServerBootstrap = new ServerBootstrap();
        NioEventLoopGroup tcpBossGroup = new NioEventLoopGroup(2);
        NioEventLoopGroup tcpWorkerGroup = new NioEventLoopGroup(4);
        tcpServerBootstrap
                .group(tcpBossGroup, tcpWorkerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel ch) {
                        ch.pipeline().addLast(new IdleStateHandler(10L, 0, 0, TimeUnit.SECONDS));
                        ch.pipeline().addLast(new BizMessageDecoder());
                        ch.pipeline().addLast(new BizInboundDataHandler());
                    }
                })
                .bind(port).sync();
        return tcpServerBootstrap;
    }
}
