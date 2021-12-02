package com.yipengup.protocol.packet;

import com.yipengup.protocol.command.Command;
import com.yipengup.protocol.packet.request.*;
import com.yipengup.protocol.packet.response.*;
import com.yipengup.serialize.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据包的编码解码
 *
 * @author yipengup
 * @date 2021/11/26
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PacketCodeC {

    public static final PacketCodeC PACKET_CODE_C = new PacketCodeC();

    /**
     * 魔术常量
     */
    public static final int MAGIC_NUMBER = 0x12345678;

    /**
     * 序列化映射
     */
    private static final Map<Byte, Serializer> SERIALIZER_MAP;

    /**
     * 数据包指令映射关系
     */
    private static final Map<Byte, Class<? extends Packet>> PACKET_MAP;

    static {
        SERIALIZER_MAP = new HashMap<>(2);
        SERIALIZER_MAP.put(Serializer.SERIALIZER_ALGORITHM_JSON, Serializer.DEFAULT_SERIALIZER);
        PACKET_MAP = new HashMap<>(2);
        PACKET_MAP.put(Command.LOGIN_REQUEST, LoginRequestPacket.class);
        PACKET_MAP.put(Command.LOGIN_RESPONSE, LoginResponsePacket.class);
        PACKET_MAP.put(Command.MESSAGE_REQUEST, MessageRequestPacket.class);
        PACKET_MAP.put(Command.MESSAGE_RESPONSE, MessageResponsePacket.class);
        PACKET_MAP.put(Command.CREATE_GROUP_REQUEST, CreateGroupRequestPacket.class);
        PACKET_MAP.put(Command.CREATE_GROUP_RESPONSE, CreateGroupResponsePacket.class);
        PACKET_MAP.put(Command.GROUP_MEMBER_LIST_REQUEST, GroupMemberListRequestPacket.class);
        PACKET_MAP.put(Command.GROUP_MEMBER_LIST_RESPONSE, GroupMemberListResponsePacket.class);
        PACKET_MAP.put(Command.GROUP_JOIN_MEMBER_REQUEST, GroupJoinMemberRequestPacket.class);
        PACKET_MAP.put(Command.GROUP_JOIN_MEMBER_RESPONSE, GroupJoinMemberResponsePacket.class);
        PACKET_MAP.put(Command.GROUP_DELETE_MEMBER_REQUEST, GroupDeleteMemberRequestPacket.class);
        PACKET_MAP.put(Command.GROUP_DELETE_MEMBER_RESPONSE, GroupDeleteMemberResponsePacket.class);
        PACKET_MAP.put(Command.GROUP_MESSAGE_REQUEST, GroupMessageRequestPacket.class);
        PACKET_MAP.put(Command.GROUP_MESSAGE_RESPONSE, GroupMessageResponsePacket.class);
        PACKET_MAP.put(Command.HEART_BEAT_REQUEST, HeartBeatRequestPacket.class);
        PACKET_MAP.put(Command.HEART_BEAT_RESPONSE, HeartBeatResponsePacket.class);
    }

    /**
     * 编码
     *
     * @param packet 参数的数据包
     * @return 传输出去的数据
     */
    public ByteBuf encode(ByteBufAllocator byteBufAllocator, Packet packet) {
        // 从ByteBuf管理器中获取到一个适合IO的直接缓冲区
        ByteBuf byteBuf = byteBufAllocator.ioBuffer();
        // 当前采用默认序列化方式
        return getByteBuf(packet, byteBuf);
    }

    /**
     * 编码
     *
     * @param packet 参数的数据包
     * @return 传输出去的数据
     */
    public ByteBuf encode0(Packet packet, ByteBuf byteBuf) {
        return getByteBuf(packet, byteBuf);
    }

    private ByteBuf getByteBuf(Packet packet, ByteBuf byteBuf) {
        // 当前采用默认序列化方式
        Serializer defaultSerializer = Serializer.DEFAULT_SERIALIZER;
        byte[] bytes = defaultSerializer.serialize(packet);
        // 数据报 = 魔术常量（4字节）+ 版本号（1字节） + 序列化方式（1字节） + 指令类型（1字节） + 数据长度（4字节） + 真实数据（byte[]）
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(defaultSerializer.getSerializerAlgorithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);

        return byteBuf;
    }


    /**
     * 解码
     *
     * @param byteBuf 获取到的二进制数据
     * @return 接收到的数据包
     */
    public Packet decode(ByteBuf byteBuf) {

        // 数据报 = 魔术常量（4字节）+ 版本号（1字节） + 序列化方式（1字节） + 指令类型（1字节） + 数据长度（4字节） + 真实数据（byte[]）
        int magicNumber = byteBuf.readInt();
        // System.out.println("magicNumber = " + magicNumber);

        byte version = byteBuf.readByte();
        // System.out.println("version = " + version);

        // 需要根据序列化算法计算出序列化方式
        byte serializerAlgorithm = byteBuf.readByte();
        Serializer serializer = SERIALIZER_MAP.get(serializerAlgorithm);

        byte command = byteBuf.readByte();
        Class<? extends Packet> clazz = PACKET_MAP.get(command);

        int dataLength = byteBuf.readInt();
        // System.out.println("dataLength = " + dataLength);

        byte[] bytes = new byte[dataLength];
        byteBuf.readBytes(bytes);
        Packet packet = serializer.deserialize(bytes, clazz);
        // System.out.println("packet = " + packet);
        return packet;
    }

}
