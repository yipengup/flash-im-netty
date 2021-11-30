package com.yipengup.protocol.packet.handler.visitor;

import com.yipengup.protocol.command.Command;
import com.yipengup.protocol.packet.Packet;
import com.yipengup.protocol.packet.PacketCodeC;
import com.yipengup.protocol.packet.request.MessageRequestPacket;
import com.yipengup.protocol.packet.response.MessageResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import java.util.Date;

/**
 * 消息请求包处理器
 *
 * @author yipengup
 * @date 2021/11/29
 */
public class MessageRequestPacketHandlerVisitor implements PacketHandlerVisitor {

    @Override
    public void handleClientPacket(Packet packet, ChannelHandlerContext ctx) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void handleServerPacket(Packet packet, ChannelHandlerContext ctx) {
        MessageRequestPacket messageRequestPacket = (MessageRequestPacket) packet;
        System.out.println(new Date() + "：收到客户端消息：" + messageRequestPacket.getMessage());

        // 向客户端发送响应消息
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setMessage("服务端回复【" + messageRequestPacket.getMessage() + "】");
        ByteBuf byteBuf = PacketCodeC.PACKET_CODE_C.encode(ctx.alloc(), messageResponsePacket);
        ctx.channel().writeAndFlush(byteBuf);
    }

    @Override
    public byte getCommand() {
        return Command.MESSAGE_REQUEST;
    }

}
