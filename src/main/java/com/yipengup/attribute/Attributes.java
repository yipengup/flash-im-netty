package com.yipengup.attribute;

import io.netty.util.AttributeKey;

/**
 * @author yipengup
 * @date 2021/11/30
 */
public interface Attributes {
    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
}
