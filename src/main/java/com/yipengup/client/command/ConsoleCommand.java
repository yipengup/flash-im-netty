package com.yipengup.client.command;

import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author yipengup
 * @date 2021/12/2
 */
public interface ConsoleCommand {
    void exec(Scanner scanner, Channel channel);
}
