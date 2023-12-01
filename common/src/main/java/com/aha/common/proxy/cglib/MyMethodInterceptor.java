package com.aha.common.proxy.cglib;

import com.aha.common.proxy.jdk.TimeUtil;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MyMethodInterceptor<T> implements MethodInterceptor {

    private T target;

    public MyMethodInterceptor(T target) {
        this.target = target;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("代理执行" + method.getName() + "方法");
        // 代理过程中插入监测方法,计算该方法耗时
        TimeUtil.start();
        Object result = method.invoke(target, objects);
        TimeUtil.finish(method.getName());

        return result;
    }

}
