package com.yipengup;

import com.yipengup.channelhandler.FirstClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author yipengup
 * @date 2021/11/17
 */
public class NettyClient {


    public static void main(String[] args) {

        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();

        // 指定客户端的 线程模型、IO模型、业务处理逻辑
        bootstrap.group(workerGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        // 连接建立后，可以与服务端进行交互
                        // channelPipeline 表示这条连接的逻辑处理链（采用责任链模式）
                        ChannelPipeline channelPipeline = ch.pipeline();
                        // 调用addLast（）方法添加一个逻辑处理器
                        channelPipeline.addLast(new FirstClientHandler());
                    }
                });

        bootstrap.connect(NettyServer.ADDRESS, NettyServer.PORT).addListener((future) -> {
            if (future.isSuccess()) {
                System.out.println("连接成功");
            } else {
                System.out.println("连接失败");
            }
        });

    }
}
