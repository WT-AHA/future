package com.aha.common.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 动态代理增强类, 只要是实现接口的类都能使用此类被增强
 *
 * @author WT
 * @date 2022/04/21 17:31:37
 */
public class MyInvocationHandler<T> implements InvocationHandler {

    // invocationHandler 持有被代理对象
    private T target;

    public MyInvocationHandler(T target) {
        this.target = target;
    }

    /**
     * proxy: 代表动态代理对象
     * method: 代表正在执行的方法
     * args: 代表调用目标方法时传入的实参
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("代理执行" + method.getName() + "方法");
        // 代理过程中插入监测方法,计算该方法耗时
        TimeUtil.start();
        Object result = method.invoke(target, args);
        TimeUtil.finish(method.getName());
        return result;

    }

}
