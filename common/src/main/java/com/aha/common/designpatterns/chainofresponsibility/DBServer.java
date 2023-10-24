package com.aha.common.designpatterns.chainofresponsibility;

/**
 * 模拟从数据库中查询数据来校验数据的合法性
 *
 * @author WT
 * date 2021/11/16
 */
public class DBServer {

    public Boolean validateUsernameAndPassword (String username, String password) {
        return true;
    }

    public Boolean validateEmail (String email) {
        return false;
    }

}
