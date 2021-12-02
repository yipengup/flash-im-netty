package com.yipengup.client.command;

import com.yipengup.protocol.packet.request.GroupDeleteMemberRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author yipengup
 * @date 2021/12/2
 */
public class GroupDeleteMemberConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("【聊天室人员删除】请输入待删除人员的聊天室id：");
        String groupId = scanner.nextLine();
        System.out.println("【聊天室人员删除】请输入待删除人员的userId：");
        String userId = scanner.nextLine();

        GroupDeleteMemberRequestPacket groupDeleteMemberRequestPacket = new GroupDeleteMemberRequestPacket(groupId, userId);
        channel.writeAndFlush(groupDeleteMemberRequestPacket);
    }
}
