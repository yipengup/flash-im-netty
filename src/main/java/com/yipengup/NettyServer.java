package com.yipengup;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * 服务端启动流程
 *
 * @author yipengup
 * @date 2021/11/13
 */
public class NettyServer {

    public static void main(String[] args) {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        // 服务端引导类，引导服务端启动工作
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap
                // 指定服务端的线程模型。分别设置两个线程组：1、boss线程组专门用于接收客户端请求 2、worker线程组专门用于处理具体的工作，比如：数据读写
                .group(bossGroup, workerGroup)
                // 指定服务端的IO模型。NioServerSocketChannel 指定为NIO, OioServerSocketChannel 指定为BIO
                .channel(NioServerSocketChannel.class)
                // 指定连接读写的处理逻辑。worker线程组实际工作的内容
                // ChannelInitializer中泛型表示当前数据channel的类型，因为上面指定为 NioServerSocketChannel，所以此处为 服务端与客户端的数据Channel就是NioSocketChannel
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        Attribute<String> clientName = nioSocketChannel.attr(AttributeKey.valueOf("clientName"));
                        System.out.println(clientName);
                    }
                });

        // handler用于指定服务端启动过程中的一些逻辑（前置钩子），一般用不上
        serverBootstrap.handler(new ChannelInitializer<NioServerSocketChannel>() {
            @Override
            protected void initChannel(NioServerSocketChannel ch) throws Exception {
                System.out.println(ch.attr(AttributeKey.valueOf("serverName")));
                System.out.println("服务端启动中");
            }
        });

        // 为ServerSocket设置一些属性，在处理服务端Channel的时候，ServerChannel里面就可以获取到这个属性
        serverBootstrap.attr(AttributeKey.newInstance("serverName"), "nettyServer");
        // 为工作线程中的数据Channel设置一些属性，在处理服务端和客户端交互时，Channel就可以获取到这些数据
        serverBootstrap.childAttr(AttributeKey.newInstance("clientName"), "nioSocketChannel");

        // 给服务端和客户端的每条连接设置一些TCP底层相关的属性
        serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
        serverBootstrap.childOption(ChannelOption.TCP_NODELAY, true);

        // 给服务端channel设置一些属性
        serverBootstrap.option(ChannelOption.SO_BACKLOG, 1024);

        // 指定服务端监听的端口，添加一个异步监听器，观察端口是否绑定成功
        serverBootstrap.bind(8000)
                // 添加一个监听器，可以判断是否绑定成功
                .addListener(new GenericFutureListener<Future<? super Void>>() {
                    @Override
                    public void operationComplete(Future<? super Void> future) throws Exception {
                        System.out.println("===========绑定端口异步线程：" + Thread.currentThread().getName());
                        if (future.isSuccess()) {
                            System.out.println("端口绑定成功");
                        } else {
                            System.out.println("端口绑定失败");
                        }
                    }
                });
    }

}
