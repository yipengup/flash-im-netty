package com.yipengup.client.handler;

import com.yipengup.protocol.packet.response.CreateGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;
import java.util.Objects;

/**
 * @author yipengup
 * @date 2021/12/2
 */
public class CreateGroupResponsePacketHandler extends SimpleChannelInboundHandler<CreateGroupResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupResponsePacket msg) throws Exception {
        System.out.println(new Date() + "：收到创建聊天室的响应信息！！！");
        if (Objects.isNull(msg.getSuccess()) || !msg.getSuccess()) {
            System.out.println(new Date() + "：聊天室创建失败。失败原因：" + msg.getReason());
            return;
        }
        System.out.println(new Date() + "：" + msg.getMessage());
    }
}
