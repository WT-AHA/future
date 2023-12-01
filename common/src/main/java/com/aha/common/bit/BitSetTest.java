package com.aha.common.bit;

import lombok.extern.slf4j.Slf4j;

import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

/**
 * bitSet 的基础概念参考： https://blog.csdn.net/m0_51545690/article/details/129583611
 * 需要了解的概念:
 *     java 中没有 bit 这种数据类型，java 实现 bitSet 这种位存储是使用 long[] 来实现的
 *     一个 long 是 8 个字节 byte 就是 64 比特位 bit
 *     bitSet.set(0) 就是 long[0] 的 最左边第 0 个比特位为 1 long[0] = 1
 *     bitSet.set(1) 就是 long[0] 的 最左边第 1 个比特位为 1 long[0] = 2
 *     bitSet.set(2) 就是 long[0] 的 最左边第 2 个比特位为 1 long[0] = 4
 *     bitSet.set(64) 就是 long[1] 的 最左边第 1 个比特位为 1 long[1] = 1
 *     bitSet.set(65) 就是 long[1] 的 最左边第 2 个比特位为 1 long[1] = 2
 */
@Slf4j
public class BitSetTest {

    // 分片  前面三位为号段  使用 short 后面使用 bitSet
    private static final Map<Short, BitSet> map = new HashMap<>();

    /**
     * 判断手机号是否重复
     * @param before 号段，手机号的前三位，例如 151
     * @param after  号码，手机号的后八位，例如 39705191
     */
    public static void isRepeat (short before, int after) {

        // 判断有没有 key 为 151 的没有的话，创建一个并放到 map 中，已经存在的话，直接返回存在的 value
        BitSet bitSet = map.computeIfAbsent(before, k -> new BitSet());
        if (bitSet.get(after)) {
            // 存在，就是重复的数据，不进行入库或者后续的业务操作
            log.info("存在重复的手机号: {}", before + "" + after);
        } else {
            // 不存在，没有重复添加到对应的 bitSet 中
            log.info("不存在重复的手机号: {}", before + "" + after);
            bitSet.set(after);
        }
    }

    public static void main(String[] args) {

        BitSet bitSet = new BitSet();

        bitSet.set(64);
        bitSet.set(65);

        // 151 3970 5192   需要的位数  其实就是 9999 9999 位 bit
        // 1 0000 0000 / 1000 / 1000 = 100 / 8 = 12.5 M * 前面三位 最大 999 也就是 1250 M (所有的号码只需要这些就可以)
        bitSet.set(39705192);

        bitSet.set(49692095);

        // 读取手机号，执行 isRepeat 的方法
        short s1 = 151;
        isRepeat(s1, 39705192);
        short s2 = 138;
        isRepeat(s2, 49692095);
        isRepeat(s1, 39705193);
        isRepeat(s1, 39705192);


    }

}
