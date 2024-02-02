package com.aha.common.implement;

import com.aha.common.proxy.jdk.Apple;
import com.aha.common.proxy.jdk.Fruit;

/**
 * 主要测试匿名内部类，实现接口，匿名内部类调用 protect 方法
 */
public class TestImplement {

    public static void main(String[] args) {

        // 匿名内部类 实现接口 创建对象
        Fruit fruit = new Fruit() {
            @Override
            public int price(int price) {
                return 0;
            }
        };

        // Apple 类的构造方法是 protected, 不同包的是没有办法调用的，但是我们可以使用 匿名内部类继承的方式创建子类，然后就可以调用它的 protected 方法了
        Apple apple = new Apple() {

            @Override
            public void testProtectedMethod () {
                // 调用 父类的 protected 方法
                super.testProtectedMethod();
            }

        };


    }

}
