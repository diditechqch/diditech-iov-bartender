package com.diditech.iov.gps.app.subscribe;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.diditech.iov.gps.api.cmd.domain.R;
import dd.utils.StrUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class BizMessageDecoder extends ByteToMessageDecoder {

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        String msg = ByteBufUtil.hexDump(in);
        String jsonString = StrUtil.hexStrToAscStr(msg);
        R r;
        try {
            r = JSON.parseObject(jsonString, R.class);
        } catch (JSONException ex) {
            r = null;
        }
        if (ObjectUtil.isEmpty(r)) {
            in.skipBytes(msg.length() / 2);
            return;
        }
        log.debug("receive biz msg: {}", jsonString);
        out.add(r);
        in.readBytes(msg.length() / 2).release();
    }
}
