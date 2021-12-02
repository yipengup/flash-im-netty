package com.yipengup.server.handler;

import com.yipengup.protocol.packet.request.CreateGroupRequestPacket;
import com.yipengup.protocol.packet.response.CreateGroupResponsePacket;
import com.yipengup.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.DefaultChannelGroup;

import java.util.*;

/**
 * @author yipengup
 * @date 2021/12/2
 */
public class CreateGroupRequestPacketHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequestPacket msg) throws Exception {
        System.out.println(new Date() + "：收到创建组的请求");
        List<String> userIds = msg.getUserIds();
        // 分别判断用户是否在线
        CreateGroupResponsePacket groupResponsePacket = new CreateGroupResponsePacket();
        DefaultChannelGroup channelGroup = new DefaultChannelGroup(ctx.executor());
        List<String> userNames = new ArrayList<>(userIds.size());
        for (String userId : userIds) {
            Channel channel = SessionUtil.getChannel(userId);
            if (Objects.isNull(channel) || !SessionUtil.hasLogin(channel)) {
                System.out.println(new Date() + "：【" + userId + "】用户不存在或者不在线");
                groupResponsePacket.setSuccess(false);
                groupResponsePacket.setReason("【" + userId + "】用户不存在或者不在线");
                ctx.channel().writeAndFlush(groupResponsePacket);
                return;
            }
            userNames.add(SessionUtil.getSession(channel).getUserName());
            channelGroup.add(channel);
        }

        // 保存聊天组的信息
        String groupId = UUID.randomUUID().toString();
        SessionUtil.bindGroup(groupId, channelGroup);

        // 向聊天组的信息发送创建成功
        groupResponsePacket.setGroupId(groupId);
        groupResponsePacket.setSuccess(true);
        groupResponsePacket.setMessage("聊天室【" + groupId + "】创建成功!!!成员列表【" + userNames + "】");
        channelGroup.writeAndFlush(groupResponsePacket);
    }
}
