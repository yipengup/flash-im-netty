package com.yipengup.protocol.packet;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 定义数据包的基类
 * <p>
 * 数据报 = 魔术常量（4字节）+ 版本号（1字节） + 序列化方式（1字节） + 数据长度（4字节） + 真实数据（byte[]）
 *
 * @author yipengup
 * @date 2021/11/25
 */
@Data
public abstract class Packet {

    @JSONField(serialize = false, deserialize = false)
    private Byte version = 1;

    @JSONField(serialize = false)
    public abstract Byte getCommand();

}
