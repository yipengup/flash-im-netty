package com.yipengup.serialize.impl;

import com.alibaba.fastjson.JSON;
import com.yipengup.serialize.Serializer;

/**
 * 使用Json序列化
 *
 * @author yipengup
 * @date 2021/11/26
 */
public class JsonSerializer implements Serializer {

    @Override
    public byte getSerializerAlgorithm() {
        return Serializer.SERIALIZER_ALGORITHM_JSON;
    }

    @Override
    public byte[] serialize(Object obj) {
        return JSON.toJSONBytes(obj);
    }

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> clazz) {
        return JSON.parseObject(bytes, clazz);
    }
}
