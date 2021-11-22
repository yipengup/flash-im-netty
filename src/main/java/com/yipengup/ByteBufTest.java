package com.yipengup;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

/**
 * Netty 关于数据读写只认 ByteBuf
 *
 * @author yipengup
 * @date 2021/11/22
 */
public class ByteBufTest {

    public static void main(String[] args) {
        // byteBuf内存管理器
        ByteBufAllocator allocator = ByteBufAllocator.DEFAULT;
        // 指定初始化容量大小和最大的容量大小
        ByteBuf byteBuf = allocator.buffer(9, 100);
        print("allocate ByteBuf(9,100)", byteBuf);

        byteBuf.writeBytes(new byte[]{1, 2, 3, 4});
        print("writeBytes(1,2,3,4)", byteBuf);

        // int类型占用4个字节
        byteBuf.writeInt(12);
        print("writeInt(12)", byteBuf);

        // 写满当前的容量， isWritable() 返回false、
        byteBuf.writeBytes(new byte[]{5});
        print("writeBytes(5)", byteBuf);

        // 不可写后则开始扩容，扩容之后capacity随即改变（当前扩容到16）
        byteBuf.writeBytes(new byte[]{6});
        print("writeBytes(6)", byteBuf);

        // get方法不改变读写指针
        System.out.println("byteBuf.getByte(3) return: " + byteBuf.getByte(3));
        System.out.println("byteBuf.getShort(3) return: " + byteBuf.getShort(3));
        System.out.println("byteBuf.getInt(3) return: " + byteBuf.getInt(3));
        print("getByte()", byteBuf);

        byteBuf.setByte(byteBuf.readableBytes() + 1, 0);
        print("setByte()", byteBuf);

        byte[] bytes = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(bytes);
        print("readBytes(" + bytes.length + ")", byteBuf);
        // 最后释放对外内存
        byteBuf.release();
    }

    private static void print(String action, ByteBuf byteBuf) {
        System.out.println("after =================" + action + "==================");
        // 获取到当前的容量大小（占用了多少字节的内存（包括丢弃的字节、可读字节、可写字节））
        System.out.println("capacity(): " + byteBuf.capacity());
        // 获取到最大的容量大小（表示 ByteBuf 底层最大能够占用多少字节的内存）
        // ByteBuf 中写数据的时候，如果发现容量不足，则进行扩容，直到扩容到 maxCapacity
        System.out.println("maxCapacity(): " + byteBuf.maxCapacity());
        // 返回当前的读指针 readerIndex
        System.out.println("readerIndex(): " + byteBuf.readerIndex());
        // readableBytes() 表示 ByteBuf 当前可读的字节数，它的值等于 writerIndex-readerIndex
        System.out.println("readableBytes(): " + byteBuf.readableBytes());
        // isReadable() 表示 byteBuf 当前是否存在可读的字节数
        System.out.println("isReadable(): " + byteBuf.isReadable());
        // 返回当前的写指针 writerIndex
        System.out.println("writerIndex(): " + byteBuf.writerIndex());
        // writableBytes() 表示 ByteBuf 当前可写的字节数，它的值等于 capacity-writerIndex
        System.out.println("writableBytes(): " + byteBuf.writableBytes());
        // isWritable() 表示 ByteBuf 当前是否存在可写的字节数（注意：当返回false时并不是一定不能写）
        System.out.println("isWritable(): " + byteBuf.isWritable());
        // maxWritableBytes() 就表示可写的最大字节数，它的值等于 maxCapacity-writerIndex
        System.out.println("maxWritableBytes(): " + byteBuf.maxWritableBytes());
    }
}
