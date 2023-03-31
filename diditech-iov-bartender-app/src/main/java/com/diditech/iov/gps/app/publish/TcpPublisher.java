package com.diditech.iov.gps.app.publish;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

/**
 * TCP 发布者
 * @author zhjd <br>
 * @date 2021/7/28 <br>
 */
@Slf4j
public class TcpPublisher {

    private final String host;
    private final int port;

    public TcpPublisher(String host, int port) {
        this.host = host;
        this.port = port;
    }

    /**
     * 连接、写、冲到缓冲区
     * @return void
     * @date 2021/8/12
     * @author zhjd
     */
    public void startWriteAndFlush(String msg) throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(host, port))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) {
                            ch.pipeline().addLast(new ClientHandler());
                        }
                    });
            ChannelFuture f = bootstrap.connect().sync();
            f.channel().writeAndFlush(Unpooled.buffer().writeBytes(msg.getBytes())).sync();
            log.debug("write and flush msg: {}", msg);
            f.channel().closeFuture();
            log.debug("channel close");
        } finally {
            group.shutdownGracefully();
            log.debug("shutdown gracefully");
        }
    }
}
