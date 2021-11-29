package com.yipengup.protocol.packet.handler.visitor;

import com.yipengup.protocol.packet.Packet;
import io.netty.channel.ChannelHandlerContext;

/**
 * 因为消息只有客户端和服务端，我们针对客户端和服务端提供不同的处理方式
 *
 * @author yipengup
 * @date 2021/11/29
 */
public interface PacketHandlerVisitor {

    /**
     * 处理客户端消息
     *
     * @param packet 传输的消息
     * @param ctx    ChannelHandlerContext
     */
    void handleClientPacket(Packet packet, ChannelHandlerContext ctx);

    /**
     * 处理服务端消息
     *
     * @param packet 传输的消息
     * @param ctx    ChannelHandlerContext
     */
    void handleServerPacket(Packet packet, ChannelHandlerContext ctx);


}
