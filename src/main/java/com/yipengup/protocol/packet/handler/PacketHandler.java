package com.yipengup.protocol.packet.handler;

import com.yipengup.protocol.packet.Packet;
import io.netty.channel.ChannelHandlerContext;

/**
 * 数据包处理
 *
 * @author yipengup
 * @date 2021/11/26
 */
public interface PacketHandler {

    /**
     * 判断是否处理当前数据包
     *
     * @param command 数据包指令
     * @return 判断是否处理
     */
    boolean accept(byte command);

    /**
     * 处理数据包
     *
     * @param packet 数据包
     */
    void handle(Packet packet, ChannelHandlerContext ctx);

}
