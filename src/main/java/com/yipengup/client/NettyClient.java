package com.yipengup.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Date;

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
                        ch.pipeline().addLast(new ClientChannelHandler());
                    }
                });

        // 客户端连接
        bootstrap.connect("127.0.0.1", 8888).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println(new Date() + "：客户端连接成功");
            } else {
                System.out.println(new Date() + "：客户端连接失败");
            }
        });


    }
}
