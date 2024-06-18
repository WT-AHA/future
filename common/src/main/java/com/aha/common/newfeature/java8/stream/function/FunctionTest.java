package com.aha.common.newfeature.java8.stream.function;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Function;

@Slf4j
public class FunctionTest {

    @Data
    @Accessors(chain = true)
    static class User {
        private String name;
    }

    public static void main(String[] args) {

        /*
         * lambda 的写法
         *
         * @param <T> the type of the input to the function
         * @param <R> the type of the result of the function
         * public interface Function<T, R> {
         */
        Function<User, String> function = User::getName;
        User user = new User().setName("aha");
        // 执行传递过来的函数，返回结果, 传入 T, 执行 function , 返回 R
        String aha = function.apply(user);
        log.info(aha);

        // 普通写法
        Function<User, String> function2 = new Function<User, String>() {
            @Override
            public String apply(User user) {
                return user.getName();
            }
        };

        User user2 = new User().setName("aha2");
        String aha2 = function2.apply(user2);
        log.info(aha2);

        // ====  测试 Function 的 compose 方法  ==== //
        /*
         * Returns a composed function that first applies the {@code before}
         * function to its input, and then applies this function to the result.
         * If evaluation of either function throws an exception, it is relayed to
         * the caller of the composed function.
         *
         * @param <V> the type of input to the {@code before} function, and to the
         *           composed function
         * @param before the function to apply before this function is applied
         * @return a composed function that first applies the {@code before}
         * function and then applies this function
         * @throws NullPointerException if before is null
         *
         * @see #andThen(Function)
         *
         * 主要功能就是组合两个函数
         * 就是一个函数的输出作为另外一个函数的输入
         * 设 f(x) = x + 1
         *    g(x) = x * 2
         * 实现 f(g(x))
         */

        Function<Integer, Integer> fx = x -> x + 1;
        Function<Integer, Integer> gx = x -> x * 2;

        // 组合函数
        Function<Integer, Integer> composeFunction = fx.compose(gx);

        // 执行组合的函数, (2 * 2) + 1
        System.out.println(composeFunction.apply(2));

        /*
         * andThen 与 compose 的作用类似也是为了组合函数，只不过他的执行顺序与 compose 相反的
         * 后执行 andThen 里面的函数
         */
        System.out.println(fx.andThen(gx).apply(2));

        /*
         * Returns a function that always returns its input argument.
         *
         * @param <T> the type of the input and output objects to the function
         * @return a function that always returns its input argument
         *
         * static <T> Function<T, T> identity() {
         *             return t -> t;
         *         }
         *
         * 作用是产生一个输入和输出相同的 Function 实例
         */
        Function<String, String> functionIdentity = Function.identity();
        System.out.println(functionIdentity.apply("functionIdentity"));

    }

}
