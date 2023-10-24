package com.aha.common.designpatterns.chainofresponsibility;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 责任链模式 - 请求时需要携带的资源，用于处理链验证和流转
 *
 * @author WT
 * date 2021/11/15
 */
@Data
@Accessors(chain = true)
public class RequestSource {

    private String username;
    private String password;
    private String email;

}
