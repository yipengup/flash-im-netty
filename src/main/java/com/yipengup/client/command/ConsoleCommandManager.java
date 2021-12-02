package com.yipengup.client.command;

import io.netty.channel.Channel;

import java.util.*;

/**
 * 用于管理控制台命令
 *
 * @author yipengup
 * @date 2021/12/2
 */
public class ConsoleCommandManager implements ConsoleCommand {

    private static final Map<String, ConsoleCommand> CONSOLE_COMMAND_MAP = new HashMap<>();

    static {
        CONSOLE_COMMAND_MAP.put("logout", new LogoutConsoleCommand());
        CONSOLE_COMMAND_MAP.put("createGroup", new CreateGroupConsoleCommand());
        CONSOLE_COMMAND_MAP.put("sentToUser", new SendToUserConsoleCommand());
    }

    @Override
    public void exec(Scanner scanner, Channel channel) {
        Set<String> commandKeys = CONSOLE_COMMAND_MAP.keySet();
        System.out.println(new Date() + "：请输入操作的指令：指令列表【" + commandKeys + "】");
        String commandKey = scanner.nextLine();
        if (!commandKeys.contains(commandKey)) {
            System.out.println(new Date() + "：指令【" + commandKey + "】不存在");
            return;
        }
        ConsoleCommand consoleCommand = CONSOLE_COMMAND_MAP.get(commandKey);
        consoleCommand.exec(scanner, channel);
    }
}
