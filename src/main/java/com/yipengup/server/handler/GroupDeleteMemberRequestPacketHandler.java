package com.yipengup.server.handler;

import com.yipengup.protocol.packet.request.GroupDeleteMemberRequestPacket;
import com.yipengup.protocol.packet.response.GroupDeleteMemberResponsePacket;
import com.yipengup.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

import java.util.Date;
import java.util.Objects;

/**
 * @author yipengup
 * @date 2021/12/2
 */
public class GroupDeleteMemberRequestPacketHandler extends SimpleChannelInboundHandler<GroupDeleteMemberRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupDeleteMemberRequestPacket msg) throws Exception {
        System.out.println(new Date() + "：收到删除聊天室【" + msg.getGroupId() + "】成员【" + msg.getUserId() + "】消息！！！");
        GroupDeleteMemberResponsePacket responsePacket = new GroupDeleteMemberResponsePacket();

        ChannelGroup channelGroup = SessionUtil.getChannelGroup(msg.getGroupId());
        if (Objects.isNull(channelGroup)) {
            responsePacket.setSuccess(false);
            responsePacket.setReason("聊天室【" + msg.getGroupId() + "】不存在");
            ctx.channel().writeAndFlush(responsePacket);
            return;
        }

        Channel channel = SessionUtil.getChannel(msg.getUserId());
        if (Objects.isNull(channel)) {
            responsePacket.setSuccess(false);
            responsePacket.setReason("用户【" + msg.getUserId() + "】不存在");
            ctx.channel().writeAndFlush(responsePacket);
            return;
        }

        channelGroup.remove(channel);
        responsePacket.setSuccess(true);
        ctx.channel().writeAndFlush(responsePacket);
    }
}
