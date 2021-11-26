package com.yipengup.protocol.packet.server;

import com.yipengup.protocol.command.Command;
import com.yipengup.protocol.packet.Packet;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author yipengup
 * @date 2021/11/26
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class LoginResponsePacket extends Packet {

    private Boolean success;
    private String description;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }
}
