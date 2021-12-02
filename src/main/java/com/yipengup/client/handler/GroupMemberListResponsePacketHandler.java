package com.yipengup.client.handler;

import com.yipengup.protocol.packet.response.GroupMemberListResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;
import java.util.Objects;

/**
 * @author yipengup
 * @date 2021/12/2
 */
@ChannelHandler.Sharable
public class GroupMemberListResponsePacketHandler extends SimpleChannelInboundHandler<GroupMemberListResponsePacket> {

    public static final GroupMemberListResponsePacketHandler INSTANCE = new GroupMemberListResponsePacketHandler();

    protected GroupMemberListResponsePacketHandler() {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMemberListResponsePacket msg) throws Exception {
        if (Objects.isNull(msg.getSuccess()) || !msg.getSuccess()) {
            System.out.println(new Date() + "：打印聊天室成员信息失败，失败原因：" + msg.getReason());
            return;
        }

        System.out.println(new Date() + "：打印聊天室成员信息成功!!!");
        System.out.println(new Date() + "：userIds：" + msg.getUserIds());
        System.out.println(new Date() + "：userNames：" + msg.getUserNames());
    }
}
