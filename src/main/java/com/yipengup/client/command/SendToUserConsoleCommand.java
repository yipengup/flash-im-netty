package com.yipengup.client.command;

import com.yipengup.protocol.packet.request.MessageRequestPacket;
import io.netty.channel.Channel;

import java.util.Date;
import java.util.Scanner;

/**
 * 向某个用户单独发送消息
 *
 * @author yipengup
 * @date 2021/12/2
 */
public class SendToUserConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println(new Date() + "：请输入要发送消息的userId：");
        String userId = scanner.nextLine();

        System.out.println(new Date() + "：请输入要发送消息：");
        String message = scanner.nextLine();
        MessageRequestPacket messageRequestPacket = new MessageRequestPacket(userId, message);
        channel.writeAndFlush(messageRequestPacket);
    }
}
