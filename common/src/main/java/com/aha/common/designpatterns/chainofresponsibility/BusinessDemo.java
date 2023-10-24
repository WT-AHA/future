package com.aha.common.designpatterns.chainofresponsibility;

import com.commons.designpatterns.chainofresponsibility.handler.ThrottlingHandler;
import com.commons.designpatterns.chainofresponsibility.handler.UserHandler;

/**
 * 模拟测试责任链的运行流程
 *
 * @author WT
 * date 2021/11/16
 */
public class BusinessDemo {

    public static void main(String[] args) {

        ThrottlingHandler throttlingHandler = new ThrottlingHandler(5);
        throttlingHandler.setNext(new UserHandler(new DBServer()));

        for (int i=0; i<10; i++) {
            throttlingHandler.doHandler(new RequestSource().setEmail("15138888@163.com").setUsername("aha").setPassword("password"));
        }

    }

}

