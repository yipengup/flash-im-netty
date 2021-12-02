package com.yipengup.client.handler;

import com.yipengup.protocol.packet.response.GroupMessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * @author yipengup
 * @date 2021/12/2
 */
public class GroupMessageResponsePacketHandler extends SimpleChannelInboundHandler<GroupMessageResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageResponsePacket msg) throws Exception {
        System.out.println(new Date() + "：收到聊天室【" + msg.getGroupId() + "】【" + msg.getFromUserName() + "】发送的消息：" + msg.getMessage());
    }
}
