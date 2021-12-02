package com.yipengup.protocol.packet.response;

import com.yipengup.protocol.command.Command;
import com.yipengup.protocol.packet.Packet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author yipengup
 * @date 2021/12/2
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class GroupMemberListResponsePacket extends Packet {

    private Boolean success;
    private String reason;
    private List<String> userIds;
    private List<String> userNames;

    @Override
    public Byte getCommand() {
        return Command.GROUP_MEMBER_LIST_RESPONSE;
    }
}
