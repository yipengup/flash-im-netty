package com.yipengup.client.handler;

import com.yipengup.protocol.packet.response.MessageResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * @author yipengup
 * @date 2021/11/30
 */
@ChannelHandler.Sharable
public class MessageResponsePacketHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {

    public static final MessageResponsePacketHandler INSTANCE = new MessageResponsePacketHandler();

    protected MessageResponsePacketHandler() {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageResponsePacket msg) throws Exception {
        System.out.println(new Date() + "：接收到【" + msg.getFromUserName() + "】消息：" + msg.getMessage());
    }
}
