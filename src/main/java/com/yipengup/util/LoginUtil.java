package com.yipengup.util;

import com.yipengup.attribute.Attributes;
import io.netty.channel.Channel;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author yipengup
 * @date 2021/11/30
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginUtil {

    /**
     * 标记已经得登录
     *
     * @param channel Channel
     */
    public static void markAsLogin(Channel channel) {
        channel.attr(Attributes.LOGIN).set(true);
    }

    /**
     * @param channel Channel
     * @return 判断是否已经登录
     */
    public static boolean hasLogin(Channel channel) {
        return channel.hasAttr(Attributes.LOGIN);
    }


}
