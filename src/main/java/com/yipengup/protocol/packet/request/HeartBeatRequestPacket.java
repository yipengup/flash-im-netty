package com.yipengup.protocol.packet.request;

import com.yipengup.protocol.command.Command;
import com.yipengup.protocol.packet.Packet;

/**
 * @author yipengup
 * @date 2021/12/2
 */
public class HeartBeatRequestPacket extends Packet {
    @Override
    public Byte getCommand() {
        return Command.HEART_BEAT_REQUEST;
    }
}

