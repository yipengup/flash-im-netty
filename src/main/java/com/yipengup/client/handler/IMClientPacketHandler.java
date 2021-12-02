package com.yipengup.client.handler;

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
public class IMClientPacketHandler extends SimpleChannelInboundHandler<Packet> {

    public static final IMClientPacketHandler INSTANCE = new IMClientPacketHandler();

    private static final Map<Byte, SimpleChannelInboundHandler<? extends Packet>> COMMAND_CHANNEL_HANDLER_MAP = new ConcurrentHashMap<>();

    static {
        COMMAND_CHANNEL_HANDLER_MAP.put(Command.CREATE_GROUP_RESPONSE, CreateGroupResponsePacketHandler.INSTANCE);
        COMMAND_CHANNEL_HANDLER_MAP.put(Command.GROUP_DELETE_MEMBER_RESPONSE, GroupDeleteMemberResponsePacketHandler.INSTANCE);
        COMMAND_CHANNEL_HANDLER_MAP.put(Command.GROUP_JOIN_MEMBER_RESPONSE, GroupJoinMemberResponsePacketHandler.INSTANCE);
        COMMAND_CHANNEL_HANDLER_MAP.put(Command.GROUP_MEMBER_LIST_RESPONSE, GroupMemberListResponsePacketHandler.INSTANCE);
        COMMAND_CHANNEL_HANDLER_MAP.put(Command.GROUP_MESSAGE_RESPONSE, GroupMessageResponsePacketHandler.INSTANCE);
        COMMAND_CHANNEL_HANDLER_MAP.put(Command.MESSAGE_RESPONSE, MessageResponsePacketHandler.INSTANCE);
    }

    protected IMClientPacketHandler() {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Packet msg) throws Exception {
        if (COMMAND_CHANNEL_HANDLER_MAP.containsKey(msg.getCommand())) {
            COMMAND_CHANNEL_HANDLER_MAP.get(msg.getCommand()).channelRead(ctx, msg);
        }
    }
}
