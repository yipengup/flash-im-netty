package com.yipengup.protocol.packet.handler;

import com.yipengup.protocol.packet.Packet;
import com.yipengup.protocol.packet.handler.response.LoginResponsePacketHandler;
import com.yipengup.protocol.packet.handler.request.LoginRequestPacketHandler;
import io.netty.channel.ChannelHandlerContext;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 数据包处理注册中心
 *
 * @author yipengup
 * @date 2021/11/26
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PacketHandlerRegister {

    /**
     * 所有的数据包处理
     */
    private static final List<PacketHandler> PACKET_HANDLERS = new ArrayList<>();

    static {
        register(new LoginRequestPacketHandler());
        register(new LoginResponsePacketHandler());
    }

    /**
     * 注册数据包处理
     *
     * @param packetHandler 数据包处理器
     */
    public static void register(PacketHandler packetHandler) {
        if (Objects.nonNull(packetHandler)) {
            PACKET_HANDLERS.add(packetHandler);
        }
    }

    /**
     * 移除数据包处理器
     *
     * @param packetHandler 数据包处理器
     */
    public static void remove(PacketHandler packetHandler) {
        if (Objects.nonNull(packetHandler)) {
            PACKET_HANDLERS.remove(packetHandler);
        }
    }

    /**
     * 找到一个数据处理器去处理数据
     *
     * @param packet packet
     */
    public static void handle(Packet packet, ChannelHandlerContext ctx) {
        if (Objects.isNull(packet) || Objects.isNull(packet.getCommand())) {
            return;
        }

        PACKET_HANDLERS.stream()
                .filter(packetHandler -> packetHandler.accept(packet.getCommand()))
                .findAny()
                .ifPresent(packetHandler -> packetHandler.handle(packet, ctx));
    }


}
