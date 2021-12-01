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

    /**
     * 消息的来源
     */
    private String fromUserId;

    /**
     * 消息发送人的名称
     */
    private String fromUserName;

    private String message;

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_RESPONSE;
    }
}
