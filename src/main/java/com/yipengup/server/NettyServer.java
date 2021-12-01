package com.yipengup.server;

import com.yipengup.codec.PacketDecode;
import com.yipengup.codec.PacketEncode;
import com.yipengup.server.handler.LoginRequestPacketHandler;
import com.yipengup.server.handler.MessageRequestPacketHandler;
import com.yipengup.server.handler.Spliter;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.nio.charset.StandardCharsets;
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
                        int length = "你好，欢迎关注我的微信公众号，《闪电侠的博客》!".getBytes(StandardCharsets.UTF_8).length;
                        // 添加固定长度的拆包器
                        // ch.pipeline().addLast(new FixedLengthFrameDecoder(length));
                        // 添加基于分割符的拆包器
                        // ch.pipeline().addLast(new DelimiterBasedFrameDecoder(Integer.MAX_VALUE, false,
                        // new ByteBuf[]{Unpooled.wrappedBuffer(new byte[]{'!'})}));
                        // ch.pipeline().addLast(new FirstServerHandler());
                        // 添加自定义协议的拆包器
                        ch.pipeline().addLast(new Spliter(Integer.MAX_VALUE, 7, 4));
                        ch.pipeline().addLast(new PacketDecode());
                        ch.pipeline().addLast(new LoginRequestPacketHandler());
                        ch.pipeline().addLast(new MessageRequestPacketHandler());
                        ch.pipeline().addLast(new PacketEncode());
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
