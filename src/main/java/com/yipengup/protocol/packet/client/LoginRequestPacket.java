package com.yipengup.protocol.packet.client;

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
public class LoginRequestPacket extends Packet {

    private Integer userId;
    private String username;
    private String password;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }
}
