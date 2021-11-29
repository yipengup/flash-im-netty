package com.yipengup.protocol.command;

/**
 * 指定指令的类型
 *
 * @author yipengup
 * @date 2021/11/25
 */
public interface Command {

    /**
     * 登录请求指令
     */
    byte LOGIN_REQUEST = 1;

    /**
     * 登录响应指令
     */
    byte LOGIN_RESPONSE = 2;

    /**
     * 消息请求指令
     */
    byte MESSAGE_REQUEST = 3;

    /**
     * 消息响应指令
     */
    byte MESSAGE_RESPONSE = 4;

}
