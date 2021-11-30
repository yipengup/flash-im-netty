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
     * 用于标识处理什么指令的数据包
     *
     * @param command command
     * @return 是否处理指定的数据包
     */
    default boolean accept(byte command) {
        return getCommand() == command;
    }

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

    byte getCommand();


}
