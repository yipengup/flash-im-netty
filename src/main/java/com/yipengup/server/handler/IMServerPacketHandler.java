package com.yipengup.server.handler;

import com.yipengup.protocol.command.Command;
import com.yipengup.protocol.packet.Packet;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yipengup
 * @date 2021/12/2
 */
@ChannelHandler.Sharable
public class IMServerPacketHandler extends SimpleChannelInboundHandler<Packet> {

    public static final IMServerPacketHandler INSTANCE = new IMServerPacketHandler();

    private static final Map<Byte, SimpleChannelInboundHandler<? extends Packet>> COMMAND_CHANNEL_HANDLER_MAP = new ConcurrentHashMap<>();

    static {
        COMMAND_CHANNEL_HANDLER_MAP.put(Command.CREATE_GROUP_REQUEST, CreateGroupRequestPacketHandler.INSTANCE);
        COMMAND_CHANNEL_HANDLER_MAP.put(Command.GROUP_DELETE_MEMBER_REQUEST, GroupDeleteMemberRequestPacketHandler.INSTANCE);
        COMMAND_CHANNEL_HANDLER_MAP.put(Command.GROUP_JOIN_MEMBER_REQUEST, GroupJoinMemberRequestPacketHandler.INSTANCE);
        COMMAND_CHANNEL_HANDLER_MAP.put(Command.GROUP_MEMBER_LIST_REQUEST, GroupMemberListRequestPacketHandler.INSTANCE);
        COMMAND_CHANNEL_HANDLER_MAP.put(Command.GROUP_MESSAGE_REQUEST, GroupMessageRequestPacketHandler.INSTANCE);
        COMMAND_CHANNEL_HANDLER_MAP.put(Command.MESSAGE_REQUEST, MessageRequestPacketHandler.INSTANCE);
    }

    protected IMServerPacketHandler() {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Packet msg) throws Exception {
        COMMAND_CHANNEL_HANDLER_MAP.get(msg.getCommand()).channelRead(ctx, msg);
    }
}
