package com.yipengup.client;

import com.yipengup.protocol.packet.Packet;
import com.yipengup.protocol.packet.PacketCodeC;
import com.yipengup.protocol.packet.client.LoginRequestPacket;
import com.yipengup.protocol.packet.handler.PacketHandlerRegister;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

/**
 * 客户端channel处理
 *
 * @author yipengup
 * @date 2021/11/26
 */
public class ClientChannelHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(new Date() + "：客户端连接到服务端");
        System.out.println("========ChannelHandlerContext==========" + ctx.getClass().getName());
        System.out.println("========ctx.channel().getClass().getName()==========" + ctx.channel().getClass().getName());
        // 组装登录请求参数
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(1);
        loginRequestPacket.setUsername("yipengup");
        loginRequestPacket.setPassword("pwd");

        ByteBuf byteBuf = PacketCodeC.PACKET_CODE_C.encode(ctx.alloc(), loginRequestPacket);
        ctx.channel().writeAndFlush(byteBuf);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(new Date() + "：客户端接收到数据");
        ByteBuf byteBuf = (ByteBuf) msg;
        Packet packet = PacketCodeC.PACKET_CODE_C.decode(byteBuf);
        PacketHandlerRegister.handle(packet, ctx);
    }
}
