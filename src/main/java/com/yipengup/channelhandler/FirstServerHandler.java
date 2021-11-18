package com.yipengup.channelhandler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * @author yipengup
 * @date 2021/11/18
 */
public class FirstServerHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 获取到客户端发送过来的byteBuf
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println(new Date() + "：服务端读到数据 -> " + byteBuf.toString(StandardCharsets.UTF_8));

        // 向客户端发送数据
        System.out.println(new Date() + "：服务端写出数据");
        ByteBuf outByteBuf = getByteBuf(ctx);
        // 通过channel去发送数据
        ctx.channel().writeAndFlush(outByteBuf);
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {

        ByteBuf byteBuf = ctx.alloc().buffer();
        byteBuf.writeBytes("hello, i am server".getBytes(StandardCharsets.UTF_8));
        return byteBuf;
    }
}
