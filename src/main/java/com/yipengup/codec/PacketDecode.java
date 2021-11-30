package com.yipengup.codec;

import com.yipengup.protocol.packet.Packet;
import com.yipengup.protocol.packet.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * 将byteBuf转化成实际的消息
 *
 * @author yipengup
 * @date 2021/11/30
 */
public class PacketDecode extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        Packet packet = PacketCodeC.PACKET_CODE_C.decode(in);
        // 将数据添加到列表中就会被向下传递
        out.add(packet);
    }
}
