package com.aha.common.newfeature.java8.stream;

/**
 * 自定义函数式接口
 * 使用 lambda 表达式来调用参数为函数式接口的方法
 *
 * 重点：就是将函数式接口当做参数传入方法中
 */
public class MyFunctionInterfaceTest {

    public static class UseLambdaInterface {

        public LambdaInterface UseLambdaInterface() {
            System.out.println("UseLambdaInterface");
            return null;
        }

        // 1. 使用的重点就是将函数式接口当做参数传入方法中,调用它唯一个的方法
        public static void useLambdaMethod (LambdaInterface lambdaInterface) {
            lambdaInterface.functionMethod();
        }

        // 2.1. 使用 :: 调用返回值为 函数式接口的方法
        public static UseLambdaInterface getLambdaInterface() {
            System.out.println("getLambdaInterface");
            return null;
        }

        // 2.2. 实例方法调用
        public LambdaInterface getLambdaInterface1() {
            System.out.println("getLambdaInterface1");
            return null;
        }

        public static void main(String[] args) {

            // 1. 使用 lambda 来调用使用 函数式接口为参数的方法
            useLambdaMethod(() -> System.out.println("调用自己定义的函数式接口"));

            // 2.1. 使用 :: 调用静态方法
            LambdaInterface lambdaInterface = UseLambdaInterface::getLambdaInterface;

            // 2.2. 实例方法调用
            UseLambdaInterface useLambdaInterface = new UseLambdaInterface();
            LambdaInterface getLambdaInterface1 = useLambdaInterface::getLambdaInterface1;

            LambdaInterface lambdaInterface2 = UseLambdaInterface::new;

        }

    }

}
