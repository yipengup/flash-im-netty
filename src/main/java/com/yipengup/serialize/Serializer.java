package com.yipengup.serialize;

import com.yipengup.serialize.impl.JsonSerializer;

/**
 * 序列化公共基类
 *
 * @author yipengup
 * @date 2021/11/25
 */
public interface Serializer {

    byte SERIALIZER_ALGORITHM_JSON = 1;

    /**
     * 默认采用Json序列化方式
     */
    Serializer DEFAULT_SERIALIZER = new JsonSerializer();

    /**
     * @return 序列化算法的种类
     */
    byte getSerializerAlgorithm();

    /**
     * 真实传输的数据包
     *
     * @param obj 数据包
     * @return 序列化后的二进制数据
     */
    byte[] serialize(Object obj);

    /**
     * 反序列化
     * 将二进制数据转化成对应的数据包
     *
     * @param bytes 二进制数据
     * @param clazz 数据包对应Class类
     * @param <T>   数据包对应的类
     * @return 数据包对象
     */
    <T> T deserialize(byte[] bytes, Class<T> clazz);


}
