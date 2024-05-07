package com.aha.common.newfeature.java8.stream.function;

import java.util.function.Supplier;

public class SupplierTest {

    public static void main(String[] args) {

        // 普通的写法
        Supplier<String> supplier = new Supplier<String>() {

            /**
             * Gets a result.
             *
             * @return a result
             */
            @Override
            public String get() {
                return "return a result";
            }

        };

        System.out.println(supplier.get());

        // lambda 的写法
        supplier = () -> "return a result 2";

        System.out.println(supplier.get());


    }

}
