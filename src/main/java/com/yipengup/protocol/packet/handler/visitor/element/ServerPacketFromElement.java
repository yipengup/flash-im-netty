package com.yipengup.protocol.packet.handler.visitor.element;

import com.yipengup.protocol.packet.Packet;
import com.yipengup.protocol.packet.handler.visitor.PacketHandlerVisitor;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author yipengup
 * @date 2021/11/29
 */
public class ServerPacketFromElement implements PacketFromElement {
    @Override
    public void operate(PacketHandlerVisitor visitor, Packet packet, ChannelHandlerContext ctx) {
        visitor.handleServerPacket(packet, ctx);
    }
}
