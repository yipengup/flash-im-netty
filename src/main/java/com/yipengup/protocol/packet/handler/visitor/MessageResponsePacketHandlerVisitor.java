package com.yipengup.protocol.packet.handler.visitor;

import com.yipengup.protocol.command.Command;
import com.yipengup.protocol.packet.Packet;
import com.yipengup.protocol.packet.response.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;

import java.util.Date;

/**
 * 消息响应处理器访问者
 *
 * @author yipengup
 * @date 2021/11/29
 */
public class MessageResponsePacketHandlerVisitor implements PacketHandlerVisitor {

    @Override
    public void handleClientPacket(Packet packet, ChannelHandlerContext ctx) {
        MessageResponsePacket messageResponsePacket = (MessageResponsePacket) packet;
        System.out.println(new Date() + "：接收到服务端消息：" + messageResponsePacket.getMessage());
    }

    @Override
    public void handleServerPacket(Packet packet, ChannelHandlerContext ctx) {
        throw new UnsupportedOperationException();
    }

    @Override
    public byte getCommand() {
        return Command.MESSAGE_RESPONSE;
    }
}
