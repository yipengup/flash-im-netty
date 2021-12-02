package com.yipengup.client.command;

import com.yipengup.protocol.packet.request.CreateGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Arrays;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

/**
 * @author yipengup
 * @date 2021/12/2
 */
public class CreateGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("请输入组员的userId列表，不同userId之间以英文逗号隔开：");
        String aggregateUserIds = scanner.nextLine();
        if (Objects.isNull(aggregateUserIds) || aggregateUserIds.length() == 0) {
            System.out.println(new Date() + ": 非法的userId列表");
            return;
        }
        String[] userIdArrays = aggregateUserIds.split(",");
        channel.writeAndFlush(new CreateGroupRequestPacket(Arrays.asList(userIdArrays)));
    }
}
