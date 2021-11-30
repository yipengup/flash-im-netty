package com.yipengup.protocol.packet.handler.visitor;

import com.yipengup.protocol.command.Command;
import com.yipengup.protocol.packet.Packet;
import com.yipengup.protocol.packet.response.LoginResponsePacket;
import com.yipengup.util.LoginUtil;
import io.netty.channel.ChannelHandlerContext;

import java.util.Date;
import java.util.Objects;

/**
 * @author yipengup
 * @date 2021/11/30
 */
public class LoginResponsePacketHandlerVisitor implements PacketHandlerVisitor {

    @Override
    public void handleClientPacket(Packet packet, ChannelHandlerContext ctx) {
        LoginResponsePacket loginResponsePacket = (LoginResponsePacket) packet;
        System.out.println(new Date() + "：收到服务端消息：" + loginResponsePacket);
        if (Objects.equals(loginResponsePacket.getSuccess(), true)) {
            System.out.println(new Date() + "：客户端登录成功！");
            // 标记登录成功状态
            LoginUtil.markAsLogin(ctx.channel());
        } else {
            System.out.println(new Date() + "：客户端登录失败，原因：" + loginResponsePacket.getDescription());
        }
    }

    @Override
    public void handleServerPacket(Packet packet, ChannelHandlerContext ctx) {
        throw new UnsupportedOperationException();
    }

    @Override
    public byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }
}
