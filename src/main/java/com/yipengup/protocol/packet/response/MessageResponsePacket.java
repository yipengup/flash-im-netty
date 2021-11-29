package com.yipengup.protocol.packet.response;

import com.yipengup.protocol.command.Command;
import com.yipengup.protocol.packet.Packet;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author yipengup
 * @date 2021/11/29
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MessageResponsePacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_RESPONSE;
    }
}
