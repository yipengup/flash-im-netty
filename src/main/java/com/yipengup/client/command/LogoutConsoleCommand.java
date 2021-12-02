package com.yipengup.client.command;

import com.yipengup.session.Session;
import com.yipengup.util.SessionUtil;
import io.netty.channel.Channel;

import java.util.Date;
import java.util.Scanner;

/**
 * @author yipengup
 * @date 2021/12/2
 */
public class LogoutConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        if (SessionUtil.hasLogin(channel)) {
            Session session = SessionUtil.getSession(channel);
            System.out.println(new Date() + "：【" + session.getUserName() + "】即将退出登录！！！");
            SessionUtil.unBindSession(session, channel);
        } else {
            System.out.println(new Date() + "：用户已经退出登录！！！");
        }
        channel.close();
    }
}
