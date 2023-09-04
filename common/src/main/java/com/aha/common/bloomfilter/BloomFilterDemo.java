package com.aha.common.bloomfilter;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

/**
 * 测试布隆过滤器的使用
 *
 * @author WT
 * date 2021/11/8
 */
@Slf4j
public class BloomFilterDemo {

    public static void main(String[] args) {

        /*
         * 创建一个插入对象为一亿,误报率为 0.01% 的布隆过滤器
         * 不存在一定不存在
         * 存在不一定存在
         * ----------------
         *  Funnel 对象：预估的元素个数，误判率
         *  mightContain ：方法判断元素是否存在
         */
        BloomFilter<CharSequence> bloomFilter = BloomFilter.create(Funnels.stringFunnel(StandardCharsets.UTF_8), 100000000, 0.0001);
        bloomFilter.put("http://www.people.com.cn/n1/2022/0524/c32306-32429073.html");
        bloomFilter.put("嗯哼");
        bloomFilter.put("Redis");

        log.info("布隆过滤器中是否包含 Redis 关键字：{}", bloomFilter.mightContain("http://www.people.com.cn/n1/2022/0524/c32306-32429073.html"));
        log.info("布隆过滤器中是否包含 Java 关键字：{}", bloomFilter.mightContain("Java"));

    }

}
