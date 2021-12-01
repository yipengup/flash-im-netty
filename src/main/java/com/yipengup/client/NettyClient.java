package com.yipengup.client;

import com.yipengup.client.handler.LoginResponsePacketHandler;
import com.yipengup.client.handler.MessageResponsePacketHandler;
import com.yipengup.codec.PacketDecode;
import com.yipengup.codec.PacketEncode;
import com.yipengup.protocol.packet.PacketCodeC;
import com.yipengup.protocol.packet.request.MessageRequestPacket;
import com.yipengup.server.handler.Spliter;
import com.yipengup.util.LoginUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Date;
import java.util.Scanner;

/**
 * netty客户端
 *
 * @author yipengup
 * @date 2021/11/26
 */
public class NettyClient {

    public static void main(String[] args) {

        NioEventLoopGroup worker = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        // 设置三要素，事件类型 + IO模型 + 业务处理
        bootstrap.group(worker)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        // 将客户端Channel处理器注册到管道中
                        // ch.pipeline().addLast(new FirstClientHandler());
                        ch.pipeline().addLast(new Spliter(Integer.MAX_VALUE, 7, 4));
                        ch.pipeline().addLast(new PacketDecode());
                        ch.pipeline().addLast(new LoginResponsePacketHandler());
                        ch.pipeline().addLast(new MessageResponsePacketHandler());
                        ch.pipeline().addLast(new PacketEncode());
                    }
                });

        // 客户端连接
        bootstrap.connect("127.0.0.1", 8888).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println(new Date() + "：客户端连接成功!");
                //    连接成功后， 向服务端发送消息
                ChannelFuture channelFuture = (ChannelFuture) future;
                Channel channel = channelFuture.channel();
                startConsoleThread(channel);
            } else {
                System.out.println(new Date() + "：客户端连接失败!");
            }
        });
    }

    private static void startConsoleThread(Channel channel) {
        new Thread(() -> {
            while (!Thread.interrupted()) {
                // 登录成功之后才能发送消息
                if (LoginUtil.hasLogin(channel)) {
                    System.out.println("输入消息发送至服务端：");
                    Scanner scanner = new Scanner(System.in);
                    // 获取到消息
                    String line = scanner.nextLine();

                    //    组装消息数据包
                    MessageRequestPacket messageRequestPacket = new MessageRequestPacket();
                    messageRequestPacket.setMessage(line);
                    ByteBuf byteBuf = PacketCodeC.PACKET_CODE_C.encode(channel.alloc(), messageRequestPacket);
                    channel.writeAndFlush(byteBuf);
                }
            }
        }).start();
    }

}
