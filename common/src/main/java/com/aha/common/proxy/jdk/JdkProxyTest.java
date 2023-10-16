package com.aha.common.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 测试自己实现的代理逻辑
 *
 * @author WT
 * @date 2022/04/21 17:35:39
 */
public class JdkProxyTest {

    public static void main(String[] args) {


        // =======   代理 apple    =======

        Apple apple = new Apple();
        MyInvocationHandler<Fruit> fruitMyInvocationHandler = new MyInvocationHandler<>(apple);
        // 这边要传入 接口的 classLoader 和 接口的 Class 所以要求被代理的类要实现一个接口
        Fruit fruitProxy = (Fruit)Proxy.newProxyInstance(Fruit.class.getClassLoader(),
                apple.getClass().getInterfaces(), fruitMyInvocationHandler);
        fruitProxy.price(1);


        System.out.println("============= 分割线 =================");

        // ====   代理 monitor    ====

        // 创建一个实例对象，这个对象是被代理的对象
        Monitor monitor = new Monitor("张三");

        // 创建一个与代理对象相关联的 InvocationHandler
        InvocationHandler myInvocationHandler = new MyInvocationHandler<Student>(monitor);

        // 创建一个代理对象 myInvocationHandler 来代理 monitor，代理对象的每个执行方法都会替换执行 Invocation 中的 invoke 方法
        Student stuProxy = (Student) Proxy.newProxyInstance(Student.class.getClassLoader(),
                new Class<?>[]{Student.class}, myInvocationHandler);

        // 代理执行上交班费的方法
        stuProxy.giveMoney();


    }

}
