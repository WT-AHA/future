package com.aha.common.juc.aqs;

/**
 * 最近看 CLH 的源码，他这个等待使用的是 while(condition);
 * 参考此文章 https://mp.weixin.qq.com/s/jEx-4XhNGOFdCo4Nou5tqg
 *
 * 很疑惑 cpu 会不会飙高，所以想测试下
 * 测试了一下  单核 cpu 会飙满
 */
public class TestWhileWait {

    public static void main(String[] args) {

        // 单核 cpu 会飙满
//        while(true);

        // 如果是多线程是不是会消耗多个 cpu 资源, 果然是 top 命令中 cpu 的消耗会变成 200%
        new Thread(() -> {
            while (true);
        }).start();

        new Thread(() -> {
            while (true);
        }).start();

        // 使用 sleep 试一下
//        try {
//            // 不会有 cpu 的消耗
//            Thread.sleep(30 * 1000 * 1000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

    }

}
