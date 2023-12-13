package com.aha.common.spring.event;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 需要发送的注册信息
 */
@Data
@Accessors(chain = true)
public class RegisterInfo {

    private String username;
    private String secret;

}
