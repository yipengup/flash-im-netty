package com.yipengup.server.handler;

import com.yipengup.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

/**
 * 对登录状态进行认证
 *
 * @author yipengup
 * @date 2021/12/1
 */
public class AuthHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (SessionUtil.hasLogin(ctx.channel())) {
            String userName = SessionUtil.getSession(ctx.channel()).getUserName();
            System.out.println(new Date() + "【" + userName + "】：用户已经登录！！！");
            // 对于已经登录的用户就不在校验登录，移除当前的channelHandler
            ctx.pipeline().remove(this);
        } else {
            System.out.println(new Date() + "：用户未登录！！！");
            ctx.channel().close();
        }
        super.channelRead(ctx, msg);
    }
}
