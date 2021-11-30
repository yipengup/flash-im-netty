package com.yipengup.protocol.packet.handler.visitor;

import com.yipengup.protocol.command.Command;
import com.yipengup.protocol.packet.Packet;
import com.yipengup.protocol.packet.PacketCodeC;
import com.yipengup.protocol.packet.request.LoginRequestPacket;
import com.yipengup.protocol.packet.response.LoginResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import java.util.Date;
import java.util.Objects;

/**
 * 登录请求处理
 *
 * @author yipengup
 * @date 2021/11/30
 */
public class LoginRequestPacketHandlerVisitor implements PacketHandlerVisitor {

    @Override
    public void handleClientPacket(Packet packet, ChannelHandlerContext ctx) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void handleServerPacket(Packet packet, ChannelHandlerContext ctx) {
        LoginRequestPacket loginRequestPacket = (LoginRequestPacket) packet;
        System.out.println(new Date() + "：收到客户端消息用户信息：" + loginRequestPacket);
        // 校验用户名和密码
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        if (valid(loginRequestPacket)) {
            System.out.println(new Date() + "：登录成功！");
            loginResponsePacket.setSuccess(true);
        } else {
            System.out.println(new Date() + "：登录失败！");
            loginResponsePacket.setSuccess(false);
            loginResponsePacket.setDescription("账号或者密码错误");
        }

        PacketCodeC packetCodeC = PacketCodeC.PACKET_CODE_C;
        ByteBuf byteBuf = packetCodeC.encode(ctx.alloc(), loginResponsePacket);
        ctx.channel().writeAndFlush(byteBuf);
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return Objects.equals(loginRequestPacket.getUsername(), "yipengup")
                && Objects.equals(loginRequestPacket.getPassword(), "pwd");
    }

    @Override
    public byte getCommand() {
        return Command.LOGIN_REQUEST;
    }
}
