package com.yipengup.protocol.packet.handler.client;

import com.yipengup.protocol.command.Command;
import com.yipengup.protocol.packet.Packet;
import com.yipengup.protocol.packet.handler.PacketHandler;
import com.yipengup.protocol.packet.server.LoginResponsePacket;
import io.netty.channel.ChannelHandlerContext;

import java.util.Date;

/**
 * 客户端登录响应处理器
 *
 * @author yipengup
 * @date 2021/11/26
 */
public class LoginResponsePacketHandler implements PacketHandler {

    @Override
    public boolean accept(byte command) {
        return Command.LOGIN_RESPONSE == command;
    }

    @Override
    public void handle(Packet packet, ChannelHandlerContext ctx) {
        LoginResponsePacket loginResponsePacket = (LoginResponsePacket) packet;
        System.out.println(new Date() + "：客户端收到消息" + loginResponsePacket);
    }
}
