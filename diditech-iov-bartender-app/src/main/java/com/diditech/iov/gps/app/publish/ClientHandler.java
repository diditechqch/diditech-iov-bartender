package com.diditech.iov.gps.app.publish;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * TCP上行消息处理器，用于接收平台应答
 * @author zhjd <br>
 * @date 2021/8/12 <br>
 */
@Slf4j
@ChannelHandler.Sharable
public class ClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        log.debug("Client inbound channel is active");
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, ByteBuf in) {
        log.info("Client inbound receive: {}", ByteBufUtil.hexDump(in));
        in.release();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.error("Client inbound channel caught exception", cause);
        ctx.close();
    }
}
