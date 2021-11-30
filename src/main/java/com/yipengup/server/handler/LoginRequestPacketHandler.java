package com.yipengup.server.handler;

import com.yipengup.protocol.packet.request.LoginRequestPacket;
import com.yipengup.protocol.packet.response.LoginResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;
import java.util.Objects;

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
        System.out.println(new Date() + "：收到客户端消息用户信息：" + msg);
        // 校验用户名和密码
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        if (valid(msg)) {
            System.out.println(new Date() + "：登录成功！");
            loginResponsePacket.setSuccess(true);
        } else {
            System.out.println(new Date() + "：登录失败！");
            loginResponsePacket.setSuccess(false);
            loginResponsePacket.setDescription("账号或者密码错误");
        }

        // 这里直接输出packet， 后续的MessageToByteEncoder会转化成ByteBuf
        ctx.channel().writeAndFlush(loginResponsePacket);
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return Objects.equals(loginRequestPacket.getUsername(), "yipengup")
                && Objects.equals(loginRequestPacket.getPassword(), "pwd");
    }
}
