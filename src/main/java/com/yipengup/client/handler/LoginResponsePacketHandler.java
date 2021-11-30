package com.yipengup.client.handler;

import com.yipengup.protocol.packet.request.LoginRequestPacket;
import com.yipengup.protocol.packet.response.LoginResponsePacket;
import com.yipengup.util.LoginUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;
import java.util.Objects;

/**
 * @author yipengup
 * @date 2021/11/30
 */
public class LoginResponsePacketHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(new Date() + "：客户端开始登录");
        // 组装登录请求参数
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(1);
        loginRequestPacket.setUsername("yipengup");
        loginRequestPacket.setPassword("pwd");

        ctx.channel().writeAndFlush(loginRequestPacket);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket msg) throws Exception {
        System.out.println(new Date() + "：收到服务端消息：" + msg);
        if (Objects.equals(msg.getSuccess(), true)) {
            System.out.println(new Date() + "：客户端登录成功！");
            // 标记登录成功状态
            LoginUtil.markAsLogin(ctx.channel());
        } else {
            System.out.println(new Date() + "：客户端登录失败，原因：" + msg.getDescription());
        }
    }
}
