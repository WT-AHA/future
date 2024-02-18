package com.aha.common.java8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * stream 中 map 与 flatMap 的区别
 * 1. map 只能实现一对一的操作，输入和输出的数据结构是不变的；flatMap 可以实现多个转化为一个
 */
public class MapAndFlatMap {

    public static void main(String[] args) {

        /**
         * map 不改变数据的结构，原本是 list 套 list 处理完之后还是 list 套 list
         * [ ['e', 'f'], ['g, 'h'] ]  ==> [[E, F], [G, H]]
         *
         * flatMap 可以做扁平化数据操作，将原本的 list 套 list 给解开扁平化 变成 一个 list
         * [ ['e', 'f'], ['g, 'h'] ] ==> [E, F, G, H]
         */
        List<List<Character>> testList = Arrays.asList(
                    Arrays.asList('e', 'f'),
                    Arrays.asList('g', 'h')
        );

        List<List<String>> testMap = testList.stream()
                .map(c -> c.stream().map(e -> e.toString().toUpperCase()).collect(Collectors.toList()))
                .collect(Collectors.toList());
        System.out.println(testMap);

//        testList.stream().flatMap(List::stream).map(e -> e.toString().toUpperCase()).collect(Collectors.toList()).forEach(System.out::println);
        List<String> testFlatMap = testList.stream()
                .flatMap(List::stream).map(e -> e.toString().toUpperCase())
                .collect(Collectors.toList());
        System.out.println(testFlatMap);

    }

}
