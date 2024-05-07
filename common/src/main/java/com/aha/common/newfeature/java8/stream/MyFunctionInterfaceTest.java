package com.aha.common.newfeature.java8.stream;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 自定义函数式接口
 * 1. 使用 lambda 表达式来调用参数为函数式接口的方法
 *  重点：就是将函数式接口当做参数传入方法中
 *
 * 2. java8 :: 使用方法的引用，它的本质是 lambda 的语法糖
 */
public class MyFunctionInterfaceTest {

    public MyFunctionInterfaceTest myFunctionInterfaceTest() {
        System.out.println("UseLambdaInterface");
        return null;
    }

    // 1. 使用的重点就是将函数式接口当做参数传入方法中,调用它唯一个的方法
    public static void useLambdaMethod (LambdaInterface lambdaInterface) {
        lambdaInterface.functionMethod();
    }

    // 2.1. 使用 :: 调用返回值为 函数式接口的方法
    public static MyFunctionInterfaceTest getStaticLambdaInterface() {
        System.out.println("getLambdaInterface");
        return null;
    }

    // 2.2. 实例方法调用
    public LambdaInterface getLambdaInterface1() {
        System.out.println("getLambdaInterface1");
        return null;
    }


    public static void main(String[] args) {

        // ======  目前理解就是会返回一个函数式接口的实现，实现了需要被调用的接口， 然后传递过去 lambdaInterface.functionMethod(); ====== //

        // 1. 使用 lambda 来调用使用 函数式接口为参数的方法
        useLambdaMethod(() -> System.out.println("调用自己定义的函数式接口"));

        // ====== 目前理解就是会返回一个函数式接口的实现，实现了需要被调用的接口， 然后传递过去被 apply 调用 ====== //

        // 2.1. 使用 :: 调用静态方法
        LambdaInterface lambdaInterface = MyFunctionInterfaceTest::getStaticLambdaInterface;

        // 2.2. 实例方法调用
        MyFunctionInterfaceTest useLambdaInterface = new MyFunctionInterfaceTest();
        LambdaInterface getLambdaInterface1 = useLambdaInterface::getLambdaInterface1;

        // 2.3. 调用构造方法
        LambdaInterface lambdaInterface1 = MyFunctionInterfaceTest::new;

        // === === //
        List<String> strings = Arrays.asList("abc", "def", "gkh", "abc");
        // 返回符合条件的stream
        // 传入 Predicate 的实现，然后执行 filter 的方法逻辑
        Stream<String> stringStream = strings.stream().filter(s -> "abc".equals(s));
        //计算流符合条件的流的数量
        long count = stringStream.count();

        //forEach遍历->打印元素
        strings.stream().forEach(System.out::println);

        //limit 获取到1个元素的stream
        Stream<String> limit = strings.stream().limit(1);
        //toArray 比如我们想看这个limitStream里面是什么，比如转换成String[],比如循环
        String[] array = limit.toArray(String[]::new);

        //map 对每个元素进行操作返回新流
        Stream<String> map = strings.stream().map(s -> s + "22");

        //sorted 排序并打印
        strings.stream().sorted().forEach(System.out::println);

        //Collectors collect 把abc放入容器中
        List<String> collect = strings.stream().filter(string -> "abc".equals(string)).collect(Collectors.toList());
        //把list转为string，各元素用，号隔开
        String mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(","));

        //对数组的统计，比如用
        List<Integer> number = Arrays.asList(1, 2, 5, 4);

        IntSummaryStatistics statistics = number.stream().mapToInt((x) -> x).summaryStatistics();
        System.out.println("列表中最大的数 : "+statistics.getMax());
        System.out.println("列表中最小的数 : "+statistics.getMin());
        System.out.println("平均数 : "+statistics.getAverage());
        System.out.println("所有数之和 : "+statistics.getSum());

        //concat 合并流
        List<String> strings2 = Arrays.asList("xyz", "jqx");
        Stream.concat(strings2.stream(),strings.stream()).count();

        //注意 一个Stream只能操作一次，不能断开，否则会报错。
        Stream stream = strings.stream();
        //第一次使用
        stream.limit(2);
        //第二次使用
        stream.forEach(System.out::println);
        //报错 java.lang.IllegalStateException: stream has already been operated upon or closed

        //但是可以这样, 连续使用
        stream.limit(2).forEach(System.out::println);

    }

}
