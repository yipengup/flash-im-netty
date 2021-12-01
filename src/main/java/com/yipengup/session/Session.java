package com.yipengup.session;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yipengup
 * @date 2021/12/1
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Session {

    /**
     * 用户唯一标识
     */
    private String userId;

    /**
     * 用户名
     */
    private String userName;

}
