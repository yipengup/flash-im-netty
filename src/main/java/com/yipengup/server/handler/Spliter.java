package com.yipengup.server.handler;

import com.yipengup.protocol.packet.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * 校验魔术常量，去掉无用的请求信息
 *
 * @author yipengup
 * @date 2021/12/1
 */
public class Spliter extends LengthFieldBasedFrameDecoder {

    public Spliter(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength) {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        if (in.getInt(in.readerIndex()) != PacketCodeC.MAGIC_NUMBER) {
            // 非法的协议,直接关闭连接
            ctx.channel().close();
            return null;
        }
        return super.decode(ctx, in);
    }
}
