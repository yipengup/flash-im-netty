package com.yipengup.server;

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
                        // 将服务端Channel处理器注册到管道中
                        // ch.pipeline().addLast(new ServerChannelHandler());

                        // 添加InboundHandler
                        ch.pipeline().addLast(new InboundHandlerA());
                        ch.pipeline().addLast(new InboundHandlerB());
                        ch.pipeline().addLast(new InboundHandlerC());

                        // 添加outBound
                        ch.pipeline().addLast(new OutBoundHandlerA());
                        ch.pipeline().addLast(new OutBoundHandlerB());
                        ch.pipeline().addLast(new OutBoundHandlerC());
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
