package com.yipengup.client.command;

import com.yipengup.protocol.packet.request.LoginRequestPacket;
import io.netty.channel.Channel;

import java.util.Date;
import java.util.Scanner;

/**
 * @author yipengup
 * @date 2021/12/2
 */
public class LoginConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {

        System.out.println(new Date() + "：请输入姓名：");
        String username = scanner.nextLine();
        // 这里密码固定，无须输入
        channel.writeAndFlush(new LoginRequestPacket(username, "pwd"));
        waitLoginResponse();
    }

    private void waitLoginResponse() {
        // 当前线程睡眠一段时间再次选择发送消息的对象
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
