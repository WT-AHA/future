package com.aha.common.newfeature.java8.stream.function;

import java.util.function.Consumer;

public class ConsumerTest {

    public static void main(String[] args) {

        Consumer<String> consumer = new Consumer<String>() {

            /**
             * 普通写法
             * Performs this operation on the given argument.
             *
             * @param s the input argument
             */
            @Override
            public void accept(String s) {
                System.out.println("consumer 消费：" + s);
            }

        };

        consumer.accept("cake");

        // lambda 表达式写法：
        Consumer<String> consumer2 = str -> System.out.println("consumer2 消费：" + str);
        consumer2.accept("money");

        // 使用 andThen 来进行组合，andThen 里面的函数是 after 执行的
        consumer.andThen(consumer2).accept("andThen");

    }

}
