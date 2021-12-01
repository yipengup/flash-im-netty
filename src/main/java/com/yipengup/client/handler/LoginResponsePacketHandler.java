package com.yipengup.client.handler;

import com.yipengup.protocol.packet.response.LoginResponsePacket;
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
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket msg) throws Exception {
        System.out.println(new Date() + "：收到服务端消息：" + msg);
        if (Objects.equals(msg.getSuccess(), true)) {
            System.out.println(new Date() + "：【" + msg.getUserName() + "】登录成功！！！userId = " + msg.getUserId());
        } else {
            System.out.println(new Date() + "：【" + msg.getUserName() + "】登录失败！！！errMsg = " + msg.getDescription());
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(new Date() + "：客户端连接被关闭！！！");

    }
}
