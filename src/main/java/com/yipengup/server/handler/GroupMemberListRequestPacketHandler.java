package com.yipengup.server.handler;

import com.yipengup.protocol.packet.request.GroupMemberListRequestPacket;
import com.yipengup.protocol.packet.response.GroupMemberListResponsePacket;
import com.yipengup.session.Session;
import com.yipengup.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

/**
 * @author yipengup
 * @date 2021/12/2
 */
@ChannelHandler.Sharable
public class GroupMemberListRequestPacketHandler extends SimpleChannelInboundHandler<GroupMemberListRequestPacket> {

    public static final GroupMemberListRequestPacketHandler INSTANCE = new GroupMemberListRequestPacketHandler();

    protected GroupMemberListRequestPacketHandler() {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMemberListRequestPacket msg) throws Exception {
        System.out.println(new Date() + "：收到打印聊天室【" + msg.getGroupId() + "】成员消息！！！");

        ChannelGroup channelGroup = SessionUtil.getChannelGroup(msg.getGroupId());
        GroupMemberListResponsePacket groupMemberListResponsePacket = new GroupMemberListResponsePacket();
        if (Objects.isNull(channelGroup)) {
            groupMemberListResponsePacket.setSuccess(false);
            groupMemberListResponsePacket.setReason("聊天室【" + msg.getGroupId() + "】不存在");
            ctx.channel().writeAndFlush(groupMemberListResponsePacket);
            return;
        }
        ArrayList<String> userIds = new ArrayList<>();
        ArrayList<String> userNames = new ArrayList<>();
        for (Channel channel : channelGroup) {
            Session session = SessionUtil.getSession(channel);
            userIds.add(session.getUserId());
            userNames.add(session.getUserName());
        }
        groupMemberListResponsePacket.setSuccess(true);
        groupMemberListResponsePacket.setUserIds(userIds);
        groupMemberListResponsePacket.setUserNames(userNames);
        ctx.channel().writeAndFlush(groupMemberListResponsePacket);
    }
}
