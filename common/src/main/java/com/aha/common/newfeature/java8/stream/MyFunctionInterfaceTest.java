package com.aha.common.newfeature.java8.stream;

/**
 * 自定义函数式接口
 * 使用 lambda 表达式来调用
 * 重点：就是将函数式接口当做参数传入方法中
 */
public class MyFunctionInterfaceTest {

    @FunctionalInterface
    public interface LambdaInterface {

        void functionMethod();

    }

    public static class UseLambdaInterface {

        // 使用的重点就是将函数式接口当做参数传入方法中,调用它唯一个的方法
        public static void useLambdaMethod (LambdaInterface lambdaInterface) {
            lambdaInterface.functionMethod();
        }

        public static void main(String[] args) {
            useLambdaMethod(() -> System.out.println("调用自己定义的函数式接口"));
        }

    }

}
