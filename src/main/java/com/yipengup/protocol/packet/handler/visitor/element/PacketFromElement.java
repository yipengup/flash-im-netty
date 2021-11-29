package com.yipengup.protocol.packet.handler.visitor.element;

import com.yipengup.protocol.packet.Packet;
import com.yipengup.protocol.packet.handler.visitor.PacketHandlerVisitor;
import io.netty.channel.ChannelHandlerContext;

/**
 * 数据包来源元素
 *
 * @author yipengup
 * @date 2021/11/29
 */
public interface PacketFromElement {

    /**
     * 具体针对某个行为的操作
     *
     * @param visitor 数据包处理的访问类型
     * @param packet  数据包
     * @param ctx     ChannelHandlerContext
     */
    void operate(PacketHandlerVisitor visitor, Packet packet, ChannelHandlerContext ctx);

}
