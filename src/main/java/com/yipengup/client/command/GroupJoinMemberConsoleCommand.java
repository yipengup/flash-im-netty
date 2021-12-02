package com.yipengup.client.command;

import com.yipengup.protocol.packet.request.GroupJoinMemberRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author yipengup
 * @date 2021/12/2
 */
public class GroupJoinMemberConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("【聊天室人员添加】请输入待添加人员的聊天室id：");
        String groupId = scanner.nextLine();
        System.out.println("【聊天室人员添加】请输入待添加人员的userId：");
        String userId = scanner.nextLine();

        channel.writeAndFlush(new GroupJoinMemberRequestPacket(groupId, userId));
    }
}
