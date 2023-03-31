package com.diditech.iov.gps.app.subscribe;

import cn.hutool.core.convert.Convert;
import com.diditech.iov.gps.api.cmd.domain.R;
import com.google.common.cache.Cache;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 入栈数据处理器
 * @author zhjd
 * @date 2021/10/13
 */
@ChannelHandler.Sharable
@Slf4j
@Component
public class BizInboundDataHandler extends SimpleChannelInboundHandler<R> {

    private static Cache<String, List<R>> TcpSubscriberEnvelope;

    @Autowired
    public void setTcpSubscriberEnvelope(@Qualifier("TcpSubscriberEnvelope") Cache<String, List<R>> tcpSubscriberEnvelope) {
        BizInboundDataHandler.TcpSubscriberEnvelope = tcpSubscriberEnvelope;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, R msg) {
        log.info("inbound: {}", msg.toString());
        List<R> list = BizInboundDataHandler.TcpSubscriberEnvelope.getIfPresent(Convert.toStr(msg.getTimestamp()));
        if (list == null) {
            return;
        }
        list.add(msg);
    }
}
