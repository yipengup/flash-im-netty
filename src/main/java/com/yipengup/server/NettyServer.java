package com.yipengup.server;

import com.yipengup.codec.PacketCodeCHandler;
import com.yipengup.server.handler.*;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Date;

/**
 * netty 服务端
 *
 * @author yipengup
 * @date 2021/11/26
 */
public class NettyServer {

    public static void main(String[] args) {

        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new Spliter(Integer.MAX_VALUE, 7, 4));
                        // 单例模式，多个channel共享同一个handler
                        ch.pipeline().addLast(PacketCodeCHandler.INSTANCE);
                        ch.pipeline().addLast(LoginRequestPacketHandler.INSTANCE);
                        ch.pipeline().addLast(AuthHandler.INSTANCE);
                        ch.pipeline().addLast(MessageRequestPacketHandler.INSTANCE);
                        ch.pipeline().addLast(CreateGroupRequestPacketHandler.INSTANCE);
                        ch.pipeline().addLast(GroupMemberListRequestPacketHandler.INSTANCE);
                        ch.pipeline().addLast(GroupJoinMemberRequestPacketHandler.INSTANCE);
                        ch.pipeline().addLast(GroupDeleteMemberRequestPacketHandler.INSTANCE);
                        ch.pipeline().addLast(GroupMessageRequestPacketHandler.INSTANCE);
                    }
                });

        // 监听某个端口
        serverBootstrap.bind(8888).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println(new Date() + "：服务端监听8888端口成功!");
            } else {
                System.out.println(new Date() + "：服务端监听8888端口失败!");
            }
        });
    }
}
