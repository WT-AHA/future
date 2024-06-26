package com.aha.common.design.patterns.chainofresponsibility.handler;

import com.aha.common.design.patterns.chainofresponsibility.DBServer;
import com.aha.common.design.patterns.chainofresponsibility.Handler;
import com.aha.common.design.patterns.chainofresponsibility.RequestSource;
import lombok.extern.slf4j.Slf4j;

/**
 * 用户处理器 - 校验用户的合法性
 *
 * @author WT
 * date 2021/11/16
 */
@Slf4j
public class UserHandler extends Handler {

    private final DBServer dbServer;

    public UserHandler (DBServer dbServer) {
        this.dbServer = dbServer;
    }


    @Override
    public Boolean doHandler(RequestSource requestSource) {

        if (!dbServer.validateUsernameAndPassword(requestSource.getUsername(), requestSource.getPassword())) {
            log.error("用户名或者密码错误");
            return false;
        }

        if (!dbServer.validateEmail(requestSource.getEmail())) {
            log.error("邮箱有问题");
            return false;
        }

        return handleNext(requestSource);
    }

}
