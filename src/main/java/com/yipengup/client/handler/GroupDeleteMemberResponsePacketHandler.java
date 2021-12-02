package com.yipengup.client.handler;

import com.yipengup.protocol.packet.response.GroupDeleteMemberResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;
import java.util.Objects;

/**
 * @author yipengup
 * @date 2021/12/2
 */
public class GroupDeleteMemberResponsePacketHandler extends SimpleChannelInboundHandler<GroupDeleteMemberResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupDeleteMemberResponsePacket msg) throws Exception {
        if (Objects.isNull(msg.getSuccess()) || !msg.getSuccess()) {
            System.out.println(new Date() + "：删除聊天室成员失败，失败原因：" + msg.getReason());
            return;
        }

        System.out.println(new Date() + "：删除聊天室成员成功");
    }
}
