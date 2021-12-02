package com.yipengup.server.handler;

import com.yipengup.protocol.packet.request.GroupJoinMemberRequestPacket;
import com.yipengup.protocol.packet.response.GroupJoinMemberResponsePacket;
import com.yipengup.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

import java.util.Date;
import java.util.Objects;

/**
 * @author yipengup
 * @date 2021/12/2
 */
@ChannelHandler.Sharable
public class GroupJoinMemberRequestPacketHandler extends SimpleChannelInboundHandler<GroupJoinMemberRequestPacket> {


    public static final GroupJoinMemberRequestPacketHandler INSTANCE = new GroupJoinMemberRequestPacketHandler();

    protected GroupJoinMemberRequestPacketHandler() {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupJoinMemberRequestPacket msg) throws Exception {
        System.out.println(new Date() + "：收到添加聊天室【" + msg.getGroupId() + "】成员【" + msg.getUserId() + "】消息！！！");
        GroupJoinMemberResponsePacket responsePacket = new GroupJoinMemberResponsePacket();

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

        channelGroup.add(channel);
        responsePacket.setSuccess(true);
        ctx.channel().writeAndFlush(responsePacket);
    }
}
