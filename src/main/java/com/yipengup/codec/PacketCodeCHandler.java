package com.yipengup.codec;

import com.yipengup.protocol.packet.Packet;
import com.yipengup.protocol.packet.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;

import java.util.List;

/**
 * @author yipengup
 * @date 2021/12/2
 */
@ChannelHandler.Sharable
public class PacketCodeCHandler extends MessageToMessageCodec<ByteBuf, Packet> {

    public static final PacketCodeCHandler INSTANCE = new PacketCodeCHandler();

    protected PacketCodeCHandler() {
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet msg, List<Object> out) throws Exception {
        ByteBuf byteBuf = PacketCodeC.PACKET_CODE_C.encode0(msg, ctx.channel().alloc().ioBuffer());
        out.add(byteBuf);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        Packet packet = PacketCodeC.PACKET_CODE_C.decode(msg);
        // 将数据添加到列表中就会被向下传递
        out.add(packet);
    }
}
