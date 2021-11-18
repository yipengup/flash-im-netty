package com.yipengup.channelhandler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * 第一个客户端 socketChannel 处理器
 *
 * @author yipengup
 * @date 2021/11/17
 */
public class FirstClientHandler extends ChannelInboundHandlerAdapter {

    /**
     * 该方法会在连接建立成功之后被调用
     * <p>
     * 当前建立连接后，向服务端传输一段文字
     *
     * @param ctx ChannelHandlerContext
     * @throws Exception Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(new Date() + "：客户端写出数据");
        // 获取要传递的数据
        ByteBuf byteBuf = getByteBuf(ctx);
        // 通过channel发送数据
        ctx.channel().writeAndFlush(byteBuf);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf inByteBuf = (ByteBuf) msg;
        System.out.println(new Date() + "：客户端读到数据 -> " + inByteBuf.toString(StandardCharsets.UTF_8));
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {

        // 获取到 ByteBuf 内存管理器，作用就是分配ByteBuf并管理ByteBuf
        // ByteBuf 是 netty对二进制数据的抽象（客户端与服务端交互的二进制数据载体）
        ByteBufAllocator byteBufAllocator = ctx.alloc();
        // 借用内存管理器分配一个 byteBuf
        ByteBuf byteBuf = byteBufAllocator.buffer();
        // 准备数据
        byte[] bytes = "hello,netty!".getBytes(StandardCharsets.UTF_8);
        // 填充数据到 ByteBuf
        byteBuf.writeBytes(bytes);
        return byteBuf;
    }

}
