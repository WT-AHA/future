package com.aha.common.newfeature.java8.stream.function;

import java.util.function.Consumer;

public class ConsumerTest {

    public static void main(String[] args) {

        Consumer<String> consumer = new Consumer<String>() {

            /**
             * 普通写法
             * Performs this operation on the given argument.
             *
             * @param t the input argument
             */
            @Override
            public void accept(String s) {
                System.out.println("消费：" + s);
            }

        };

        consumer.accept("cake");

        // lambda 表达式写法：
        consumer = str -> System.out.println("消费：" + str);
        consumer.accept("money");

    }

}
