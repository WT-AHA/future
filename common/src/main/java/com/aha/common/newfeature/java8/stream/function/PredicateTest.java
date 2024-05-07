package com.aha.common.newfeature.java8.stream.function;

import java.util.function.Predicate;

public class PredicateTest {

    public static void main(String[] args) {

        // 一般的写法
        Predicate<Integer> predicate = new Predicate<Integer>() {

            /**
             * Evaluates this predicate on the given argument.
             *
             * @param t the input argument
             * @return {@code true} if the input argument matches the predicate,
             * otherwise {@code false}
             */
            @Override
            public boolean test(Integer integer) {
                return 1 == integer;
            }

        };

        System.out.println(predicate.test(1));
        System.out.println(predicate.test(2));

        // lambda 的写法
        Predicate<Integer> predicate2 = t -> 1 == t;

        System.out.println(predicate2.test(1));
        System.out.println(predicate2.test(2));

    }

}
