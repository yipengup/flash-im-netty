package com.yipengup.protocol.packet.request;

import com.yipengup.protocol.command.Command;
import com.yipengup.protocol.packet.Packet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author yipengup
 * @date 2021/12/2
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class GroupJoinMemberRequestPacket extends Packet {

    private String groupId;
    private String userId;

    @Override
    public Byte getCommand() {
        return Command.GROUP_JOIN_MEMBER_REQUEST;
    }
}
