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
                return integer > 1;
            }

        };

        System.out.println(predicate.test(1));
        System.out.println(predicate.test(2));

        // lambda 的写法
        Predicate<Integer> predicate2 = t -> t > 2;

        System.out.println(predicate2.test(1));
        System.out.println(predicate2.test(3));

        /*
         * Predicate 主要是为了做断言(判断) and 的 底层就是 &&
         *
         * default Predicate<T> and(Predicate<? super T> other) {
         *         Objects.requireNonNull(other);
         *         return (t) -> test(t) && other.test(t);
         * }
         * 2 大于 1 并且 大于 2 吗，显然是 false
         */
        System.out.println(predicate.and(predicate2).test(2));


        // test 的取反
        System.out.println(predicate.and(predicate2).negate().test(2));

        // or
        System.out.println(predicate.or(predicate2).test(2));

        // isEqual, 底层调用 equals 方法来进行比较
        System.out.println(Predicate.isEqual("1").test("1"));

        // 感觉与 negate 的用法有些重复
        System.out.println(Predicate.not(predicate).test(2));


    }

}
