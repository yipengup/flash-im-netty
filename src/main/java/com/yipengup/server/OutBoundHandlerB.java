package com.yipengup.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

import java.util.Date;

/**
 * @author yipengup
 * @date 2021/11/30
 */
public class OutBoundHandlerB extends ChannelOutboundHandlerAdapter {
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        System.out.println("OutBoundHandlerB：" + msg);
        super.write(ctx, msg, promise);
        System.out.println("OutBoundHandlerB：上一个handler处理完：" + new Date());

    }
}
