package com.yipengup;

import com.yipengup.channelhandler.FirstServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * 服务端
 *
 * @author yipengup
 * @date 2021/11/17
 */
public class NettyServer {

    public static final String ADDRESS = "127.0.0.1";
    public static final int PORT = 8000;

    public static void main(String[] args) {

        NioEventLoopGroup boosGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap
                // 指定线程模型，IO模型，业务处理逻辑
                .group(boosGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {

                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        // 连接建立后，可以与客户端进行交互
                        ch.pipeline().addLast(new FirstServerHandler());
                    }
                });

        serverBootstrap.bind(PORT).addListener((future) -> {
            if (future.isSuccess()) {
                System.out.println("服务端监听端口成功");
            } else {
                System.out.println("服务端监听端口失败");
            }
        });

    }

}
