package com.yipengup.protocol.command;

import io.netty.buffer.ByteBuf;
import org.junit.Test;

/**
 * @author yipengup
 * @date 2021/11/26
 */
public class PacketCodeCTest {

    @Test
    public void encodeAndDecode() {

        // 编码
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(1);
        loginRequestPacket.setUsername("yipengup");
        loginRequestPacket.setPassword("yipengup");

        PacketCodeC packetCodeC = new PacketCodeC();
        ByteBuf byteBuf = packetCodeC.encode(loginRequestPacket);

        // 解码
        Packet packet = packetCodeC.decode(byteBuf);
        System.out.println(packet);

    }
}
