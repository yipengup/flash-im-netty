package com.yipengup.client.handler;

import com.yipengup.protocol.packet.response.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * @author yipengup
 * @date 2021/11/30
 */
public class MessageResponsePacketHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageResponsePacket msg) throws Exception {
        System.out.println(new Date() + "：接收到服务端消息：" + msg.getMessage());

    }
}
