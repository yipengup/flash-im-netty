package com.yipengup.server.handler;

import com.yipengup.util.LoginUtil;
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
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("AuthHandler.channelRegistered");
        super.channelRegistered(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("AuthHandler.channelUnregistered");
        if (LoginUtil.hasLogin(ctx.channel())) {
            System.out.println(new Date() + "：用户已经登录！！！");
        } else {
            System.out.println(new Date() + "：用户未登录！！！");
        }
        super.channelUnregistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("AuthHandler.channelActive");
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("AuthHandler.channelInactive");
        if (LoginUtil.hasLogin(ctx.channel())) {
            System.out.println(new Date() + "：用户已经登录！！！");
        } else {
            System.out.println(new Date() + "：用户未登录！！！");
        }
        super.channelInactive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("AuthHandler.channelRead");
        if (LoginUtil.hasLogin(ctx.channel())) {
            System.out.println(new Date() + "：用户已经登录！！！");
            ctx.pipeline().remove(this);
        } else {
            System.out.println(new Date() + "：用户未登录！！！");
            ctx.channel().close();
        }
        super.channelRead(ctx, msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("AuthHandler.channelReadComplete");
        if (LoginUtil.hasLogin(ctx.channel())) {
            System.out.println(new Date() + "：用户已经登录！！！");
        } else {
            System.out.println(new Date() + "：用户未登录！！！");
        }
        super.channelReadComplete(ctx);
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("AuthHandler.handlerAdded");
        super.handlerAdded(ctx);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("AuthHandler.handlerRemoved");
        if (LoginUtil.hasLogin(ctx.channel())) {
            System.out.println(new Date() + "：用户已经登录！！！");
        } else {
            System.out.println(new Date() + "：用户未登录！！！");
        }
        super.handlerRemoved(ctx);
    }
}
