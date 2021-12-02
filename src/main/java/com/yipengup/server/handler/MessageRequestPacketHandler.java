package com.yipengup.server.handler;

import com.yipengup.protocol.packet.request.MessageRequestPacket;
import com.yipengup.protocol.packet.response.MessageResponsePacket;
import com.yipengup.session.Session;
import com.yipengup.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;
import java.util.Objects;

/**
 * @author yipengup
 * @date 2021/11/30
 */
@ChannelHandler.Sharable
public class MessageRequestPacketHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {

    public static final MessageRequestPacketHandler INSTANCE = new MessageRequestPacketHandler();

    protected MessageRequestPacketHandler() {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket msg) throws Exception {

        String toUserId = msg.getToUserId();
        // 获取到接收消息用户的channel信息
        Channel channel = SessionUtil.getChannel(toUserId);
        if (Objects.isNull(channel) || !SessionUtil.hasLogin(channel)) {
            System.out.println(new Date() + "：用户没有登录：userId = " + toUserId);
            return;
        }

        // 向客户端发送响应消息
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        Session session = SessionUtil.getSession(ctx.channel());
        messageResponsePacket.setFromUserId(session.getUserId());
        messageResponsePacket.setFromUserName(session.getUserName());
        messageResponsePacket.setMessage(msg.getMessage());
        channel.writeAndFlush(messageResponsePacket);
    }
}
