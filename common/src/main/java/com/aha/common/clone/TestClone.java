package com.aha.common.clone;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 测试 clone
 * 主要是要看一下 clone 出来的对象改变了值，原始对象是否会变化
 * 原始对象变化，克隆对象是否变化
 * 经测试，深克隆之后产生的对象与原始对象不会相互影响
 */
public class TestClone {

    @Data
    @Accessors(chain = true)
    static class User implements Serializable, Cloneable {

        private String name;
        private int age;

        @Override
        public User clone() {

            try {
                return (User) super.clone();
            } catch (CloneNotSupportedException e) {
                throw new AssertionError();
            }

        }

    }

    public static void main(String[] args) {

        User zhangsan = new User().setName("张三").setAge(18);
        User lisi = zhangsan.clone();
        lisi.setName("lisi").setAge(30);

        System.out.println(zhangsan);
        System.out.println(lisi);

        zhangsan.setAge(22);

        System.out.println(zhangsan);
        System.out.println(lisi);

    }

}
