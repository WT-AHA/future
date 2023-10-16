package com.aha.common.proxy.jdk;

/**
 * 苹果类
 *
 * @author WT
 * @date 2022/04/21 17:46:35
 */
public class Apple implements Fruit{


    /**
     * 苹果 1000 元
     * @return 1000
     */
    @Override
    public int price(int price) {
        System.out.println("这个苹果卖: " + price * 100);
        return price * 100;
    }

}
