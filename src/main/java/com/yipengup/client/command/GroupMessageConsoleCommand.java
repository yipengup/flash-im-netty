package com.yipengup.client.command;

import com.yipengup.protocol.packet.request.GroupMessageRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author yipengup
 * @date 2021/12/2
 */
public class GroupMessageConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("【聊天室消息】请输入待发消息的聊天室id：");
        String groupId = scanner.nextLine();
        System.out.println("【聊天室消息】请输入待发送的消息：");
        String message = scanner.nextLine();

        channel.writeAndFlush(new GroupMessageRequestPacket(groupId, message));
    }
}
