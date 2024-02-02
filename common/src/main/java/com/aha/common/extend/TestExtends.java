package com.aha.common.extend;

import lombok.Data;

/**
 * WT-ZJ
 * 兄弟之间不能相互转化
 * 子类可以强制转化为父类
 */
public class TestExtends {

    interface Fruit {
        String name = "水果";
    }

    @Data
    static class Apple implements Fruit {
        protected Apple () {

        }
        String name = "苹果";
    }

    @Data
    static class Banana implements Fruit {
        String name = "香蕉";
    }

    public static void main(String[] args) {
        Apple apple = new Apple();
//        兄弟之间不能相互转化
//        Banana banana = (Banana) apple;
        // 父子之间可以相互转化
        Fruit fruit = (Fruit) apple;

    }

}
