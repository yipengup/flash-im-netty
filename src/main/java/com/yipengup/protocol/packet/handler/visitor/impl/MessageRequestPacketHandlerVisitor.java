package com.yipengup.protocol.packet.handler.visitor.impl;

import com.yipengup.protocol.packet.Packet;
import com.yipengup.protocol.packet.handler.visitor.PacketHandlerVisitor;
import io.netty.channel.ChannelHandlerContext;

/**
 * 消息请求包处理器
 *
 * @author yipengup
 * @date 2021/11/29
 */
public class MessageRequestPacketHandlerVisitor implements PacketHandlerVisitor {

    @Override
    public void handleClientPacket(Packet packet, ChannelHandlerContext ctx) {

    }

    @Override
    public void handleServerPacket(Packet packet, ChannelHandlerContext ctx) {

    }
}
