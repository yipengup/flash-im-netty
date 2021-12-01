package com.yipengup.server.handler;

import com.yipengup.protocol.packet.request.LoginRequestPacket;
import com.yipengup.protocol.packet.response.LoginResponsePacket;
import com.yipengup.session.Session;
import com.yipengup.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;
import java.util.UUID;

/**
 * 会自动判断是否是指定的泛型类型，
 * 如是直接处理，
 * 如不是，调用{@link ChannelHandlerContext#fireChannelRead(java.lang.Object)} 调用下一个handler处理
 *
 * @author yipengup
 * @date 2021/11/30
 */
public class LoginRequestPacketHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket msg) throws Exception {
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        String userId = UUID.randomUUID().toString();
        loginResponsePacket.setUserId(userId);
        loginResponsePacket.setUserName(msg.getUsername());
        loginResponsePacket.setSuccess(true);
        System.out.println(new Date() + "【" + msg.getUsername() + "】：登录成功！");
        // 登录成功后需要保存用户的信息
        Session session = new Session(userId, msg.getUsername());
        SessionUtil.bindSession(session, ctx.channel());
        // 这里直接输出packet， 后续的MessageToByteEncoder会转化成ByteBuf
        ctx.channel().writeAndFlush(loginResponsePacket);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        if (SessionUtil.hasLogin(ctx.channel())) {
            String userName = SessionUtil.getSession(ctx.channel()).getUserName();
            System.out.println(new Date() + "【" + userName + "】" + "：连接被关闭！！！");
        } else {
            System.out.println(new Date() + "【未知用户】" + "：连接被关闭！！！");
        }
        SessionUtil.unBindSession(SessionUtil.getSession(ctx.channel()), ctx.channel());
    }


}
