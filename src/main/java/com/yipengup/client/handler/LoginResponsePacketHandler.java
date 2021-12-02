package com.yipengup.client.handler;

import com.yipengup.protocol.packet.response.LoginResponsePacket;
import com.yipengup.session.Session;
import com.yipengup.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;
import java.util.Objects;

/**
 * @author yipengup
 * @date 2021/11/30
 */
@ChannelHandler.Sharable
public class LoginResponsePacketHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {


    public static final LoginResponsePacketHandler INSTANCE = new LoginResponsePacketHandler();

    protected LoginResponsePacketHandler() {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket msg) throws Exception {
        if (Objects.equals(msg.getSuccess(), true)) {
            System.out.println(new Date() + "：【" + msg.getUserName() + "】登录成功！！！userId = " + msg.getUserId());
            // 客户端也需要设置登录成功的标志
            SessionUtil.bindSession(new Session(msg.getUserId(), msg.getUserName()), ctx.channel());
        } else {
            System.out.println(new Date() + "：【" + msg.getUserName() + "】登录失败！！！errMsg = " + msg.getDescription());
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(new Date() + "：连接被关闭！！！");
    }
}
