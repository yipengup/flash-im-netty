package com.yipengup.codec;

import com.yipengup.protocol.packet.Packet;
import com.yipengup.protocol.packet.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 将packet封装成ByteBuf对象
 *
 * @author yipengup
 * @date 2021/11/30
 */
public class PacketEncode extends MessageToByteEncoder<Packet> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet msg, ByteBuf out) throws Exception {
        PacketCodeC.PACKET_CODE_C.encode0(msg, out);
    }
}
