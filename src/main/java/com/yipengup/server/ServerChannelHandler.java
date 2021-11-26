package com.yipengup.server;

import com.yipengup.protocol.packet.Packet;
import com.yipengup.protocol.packet.PacketCodeC;
import com.yipengup.protocol.packet.handler.PacketHandlerRegister;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

/**
 * 服务端消息处理
 *
 * @author yipengup
 * @date 2021/11/26
 */
public class ServerChannelHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(new Date() + "：服务端接收到数据");
        ByteBuf byteBuf = (ByteBuf) msg;
        Packet packet = PacketCodeC.PACKET_CODE_C.decode(byteBuf);
        PacketHandlerRegister.handle(packet, ctx);
    }
}
