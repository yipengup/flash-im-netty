package com.yipengup;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.BootstrapConfig;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.util.concurrent.TimeUnit;

/**
 * 客户端启动流程
 *
 * @author yipengup
 * @date 2021/11/17
 */
public class NettyClient {

    public static void main(String[] args) {

        // 线程池的一种实现方式
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        // 客户端启动启动类
        Bootstrap bootstrap = new Bootstrap();
        bootstrap
                // 指定线程模型：指定客户端实际的工作的线程模型
                .group(workerGroup)
                // 指定IO模型
                .channel(NioSocketChannel.class)
                // 指定IO处理逻辑，主要就是定义连接的业务处理逻辑
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {

                    }
                });

        // 指定服务端的地址进行连接
        bootstrap.connect("127.0.0.1", 8000).addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                if (future.isSuccess()) {
                    System.out.println("连接成功");
                } else {
                    System.out.println("连接失败");
                }
            }
        });

        // 这个是客户端启动类的配置抽象类，返回配置信息
        BootstrapConfig bootstrapConfig = bootstrap.config();
        EventLoopGroup executors = bootstrapConfig.group();
        // 线程池延迟调度（只是延迟执行而已，而不是定时态度）
        executors.schedule(() -> {
            System.out.println("NettyClient.main");
        }, 5, TimeUnit.SECONDS);

        // 设置属性，也就是给 NioSocketChannel 绑定自定义属性， 在处理业务逻辑时，可以获取到对应的属性
        bootstrap.attr(AttributeKey.newInstance("clientName"), "nettyClient");

        // option 可以给连接设置一些TCP底层相关的属性
        // ChannelOption.CONNECT_TIMEOUT_MILLIS 连接的超时时间
        bootstrap.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000);
        // ChannelOption.SO_KEEPALIVE 是否开启底层心跳机制
        bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
        // 是否开启Nagle算法（如果要求高实时性，有数据发送时就马上发送，就设置为 true 关闭，如果需要减少发送次数减少网络交互，就设置为 false 开启）
        bootstrap.option(ChannelOption.TCP_NODELAY, true);

    }
}
