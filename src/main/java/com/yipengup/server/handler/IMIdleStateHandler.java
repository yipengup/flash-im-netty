package com.yipengup.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 空闲检测
 *
 * @author yipengup
 * @date 2021/12/2
 */
public class IMIdleStateHandler extends IdleStateHandler {

    /**
     * 定义15秒没有读取到数据直接就直接关掉连接
     */
    private static final int READER_IDLE_MAX_TIME = 15;

    public IMIdleStateHandler() {
        super(READER_IDLE_MAX_TIME, 0, 0, TimeUnit.SECONDS);
    }

    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) throws Exception {
        System.out.println(new Date() + "：超过【" + READER_IDLE_MAX_TIME + "】没有读取到数据，判定连接处于假死状态，即将关闭连接");
        // 关闭连接
        ctx.channel().close();
    }
}
