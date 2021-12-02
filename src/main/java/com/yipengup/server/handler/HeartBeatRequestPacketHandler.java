package com.yipengup.server.handler;

import com.yipengup.protocol.packet.request.HeartBeatRequestPacket;
import com.yipengup.protocol.packet.response.HeartBeatResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * 心跳检测包
 *
 * @author yipengup
 * @date 2021/12/2
 */
@ChannelHandler.Sharable
public class HeartBeatRequestPacketHandler extends SimpleChannelInboundHandler<HeartBeatRequestPacket> {

    public static final HeartBeatRequestPacketHandler INSTANCE = new HeartBeatRequestPacketHandler();

    protected HeartBeatRequestPacketHandler() {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HeartBeatRequestPacket msg) throws Exception {
        System.out.println(new Date() + "：收到心跳检测包！！！");
        // 一般心跳无须处理， 不用继续走接下来的pipeline中的outbound，客户端也不用处理消息，只要刷新idleState就行
        ctx.writeAndFlush(new HeartBeatResponsePacket());
    }
}
