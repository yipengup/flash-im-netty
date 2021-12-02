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

    /**
     * 创建群请求
     */
    byte CREATE_GROUP_REQUEST = 5;

    /**
     * 创建群响应
     */
    byte CREATE_GROUP_RESPONSE = 6;

    /**
     * 群成员列表请求
     */
    byte GROUP_MEMBER_LIST_REQUEST = 7;

    /**
     * 群成员列表响应
     */
    byte GROUP_MEMBER_LIST_RESPONSE = 8;

    /**
     * 添加组成员请求
     */
    byte GROUP_JOIN_MEMBER_REQUEST = 9;

    /**
     * 添加组成员响应
     */
    byte GROUP_JOIN_MEMBER_RESPONSE = 10;

    /**
     * 删除组成员请求
     */
    byte GROUP_DELETE_MEMBER_REQUEST = 11;

    /**
     * 删除组成员响应
     */
    byte GROUP_DELETE_MEMBER_RESPONSE = 12;

    byte GROUP_MESSAGE_REQUEST = 13;

    byte GROUP_MESSAGE_RESPONSE = 14;

    /**
     * 心跳请求
     */
    byte HEART_BEAT_REQUEST = 15;

    /**
     * 心跳响应
     */
    byte HEART_BEAT_RESPONSE = 16;


}
