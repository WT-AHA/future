package com.aha.common.proxy.jdk;

/**
 * jdk 动态代理: 学生的实现类 班长
 *
 * @author WT
 * @date 2022/04/21 17:28:47
 */
public class Monitor implements Student {

    private String name;

    public Monitor(String name) {
        this.name = name;
    }

    @Override
    public void giveMoney() {
        try {
            //假设数钱花了一秒时间
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + "上交班费50元");
    }

}
