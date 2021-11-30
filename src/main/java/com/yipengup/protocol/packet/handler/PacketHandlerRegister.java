package com.yipengup.protocol.packet.handler;

import com.yipengup.protocol.packet.Packet;
import com.yipengup.protocol.packet.TerminalTypeEnum;
import com.yipengup.protocol.packet.handler.visitor.*;
import com.yipengup.protocol.packet.handler.visitor.element.ClientPacketFromElement;
import com.yipengup.protocol.packet.handler.visitor.element.ServerPacketFromElement;
import io.netty.channel.ChannelHandlerContext;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
    private static final List<PacketHandlerVisitor> PACKET_HANDLERS = new ArrayList<>();
    private static final ClientPacketFromElement CLIENT_PACKET_FROM_ELEMENT = new ClientPacketFromElement();
    private static final ServerPacketFromElement SERVER_PACKET_FROM_ELEMENT = new ServerPacketFromElement();

    static {
        PACKET_HANDLERS.add(new LoginRequestPacketHandlerVisitor());
        PACKET_HANDLERS.add(new LoginResponsePacketHandlerVisitor());
        PACKET_HANDLERS.add(new MessageRequestPacketHandlerVisitor());
        PACKET_HANDLERS.add(new MessageResponsePacketHandlerVisitor());
    }

    /**
     * 注册数据包处理
     *
     * @param packetHandlerVisitor 数据包处理器
     */
    public static void register(PacketHandlerVisitor packetHandlerVisitor) {
        if (Objects.nonNull(packetHandlerVisitor)) {
            PACKET_HANDLERS.add(packetHandlerVisitor);
        }
    }

    /**
     * 移除数据包处理器
     *
     * @param packetHandlerVisitor 数据包处理器
     */
    public static void remove(PacketHandlerVisitor packetHandlerVisitor) {
        if (Objects.nonNull(packetHandlerVisitor)) {
            PACKET_HANDLERS.remove(packetHandlerVisitor);
        }
    }

    /**
     * 找到一个数据处理器去处理数据
     *
     * @param packet packet
     */
    public static void handle(Packet packet, ChannelHandlerContext ctx, TerminalTypeEnum terminalTypeEnum) {
        if (Objects.isNull(packet) || Objects.isNull(packet.getCommand())) {
            return;
        }

        Optional<PacketHandlerVisitor> any = PACKET_HANDLERS.stream()
                .filter(packetHandler -> packetHandler.accept(packet.getCommand()))
                .findAny();

        if (any.isPresent()) {
            PacketHandlerVisitor packetHandlerVisitor = any.get();
            if (terminalTypeEnum.equals(TerminalTypeEnum.CLIENT)) {
                CLIENT_PACKET_FROM_ELEMENT.operate(packetHandlerVisitor, packet, ctx);
            } else {
                SERVER_PACKET_FROM_ELEMENT.operate(packetHandlerVisitor, packet, ctx);
            }
        }

    }


}
