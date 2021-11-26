package com.yipengup.protocol.packet.handler.server;

import com.yipengup.protocol.command.Command;
import com.yipengup.protocol.packet.Packet;
import com.yipengup.protocol.packet.PacketCodeC;
import com.yipengup.protocol.packet.client.LoginRequestPacket;
import com.yipengup.protocol.packet.handler.PacketHandler;
import com.yipengup.protocol.packet.server.LoginResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import java.util.Date;
import java.util.Objects;

/**
 * 登录请求数据包处理
 *
 * @author yipengup
 * @date 2021/11/26
 */
public class LoginRequestPacketHandler implements PacketHandler {

    @Override
    public boolean accept(byte command) {
        return Command.LOGIN_REQUEST == command;
    }

    @Override
    public void handle(Packet packet, ChannelHandlerContext ctx) {
        LoginRequestPacket loginRequestPacket = (LoginRequestPacket) packet;
        System.out.println(new Date() + "：服务端接收到的用户信息 + " + loginRequestPacket);
        // 校验用户名和密码
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        if (valid(loginRequestPacket)) {
            loginResponsePacket.setSuccess(true);
        } else {
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
}
