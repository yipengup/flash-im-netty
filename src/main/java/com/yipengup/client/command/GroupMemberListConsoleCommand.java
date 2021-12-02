package com.yipengup.client.command;

import com.yipengup.protocol.packet.request.GroupMemberListRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author yipengup
 * @date 2021/12/2
 */
public class GroupMemberListConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("【聊天室人员列表】请输入展示列表的聊天室id：");
        channel.writeAndFlush(new GroupMemberListRequestPacket(scanner.nextLine()));
    }
}
