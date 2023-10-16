package com.aha.common.proxy.cglib;

import com.commons.proxy.jdk.Apple;
import org.springframework.cglib.proxy.Enhancer;

public class CglibProxyTest {

    public static void main(String[] args) throws Exception {

        Apple apple = new Apple();
        Apple appleProxy = (Apple) Enhancer.create(apple.getClass(), new MyMethodInterceptor<>(apple));
        appleProxy.price(1);

    }

}
