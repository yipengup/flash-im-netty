package com.yipengup.server.handler;

import com.yipengup.protocol.packet.request.GroupMessageRequestPacket;
import com.yipengup.protocol.packet.response.GroupMessageResponsePacket;
import com.yipengup.session.Session;
import com.yipengup.util.SessionUtil;
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
public class GroupMessageRequestPacketHandler extends SimpleChannelInboundHandler<GroupMessageRequestPacket> {

    public static final GroupMessageRequestPacketHandler INSTANCE = new GroupMessageRequestPacketHandler();

    protected GroupMessageRequestPacketHandler() {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageRequestPacket msg) throws Exception {
        System.out.println(new Date() + "：收到发送给聊天室【" + msg.getGroupId() + "】的消息");
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(msg.getGroupId());
        if (Objects.isNull(channelGroup)) {
            System.out.println(new Date() + "：聊天室【" + msg.getGroupId() + "】不存在");
            return;
        }
        GroupMessageResponsePacket responsePacket = new GroupMessageResponsePacket();
        responsePacket.setGroupId(msg.getGroupId());
        Session session = SessionUtil.getSession(ctx.channel());
        responsePacket.setFromUserId(session.getUserId());
        responsePacket.setFromUserName(session.getUserName());
        responsePacket.setMessage(msg.getMessage());

        channelGroup.writeAndFlush(responsePacket);
    }
}
