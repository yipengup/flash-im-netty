package com.yipengup.protocol.command;

import com.yipengup.protocol.packet.Packet;
import com.yipengup.protocol.packet.PacketCodeC;
import com.yipengup.protocol.packet.client.LoginRequestPacket;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
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

        PacketCodeC packetCodeC = PacketCodeC.PACKET_CODE_C;
        ByteBuf byteBuf = packetCodeC.encode(ByteBufAllocator.DEFAULT, loginRequestPacket);

        // 解码
        Packet packet = packetCodeC.decode(byteBuf);
        System.out.println(packet);

    }
}
