package com.yipengup.protocol.packet.response;

import com.yipengup.protocol.command.Command;
import com.yipengup.protocol.packet.Packet;

/**
 * @author yipengup
 * @date 2021/12/2
 */
public class HeartBeatResponsePacket extends Packet {
    @Override
    public Byte getCommand() {
        return Command.HEART_BEAT_RESPONSE;
    }
}

