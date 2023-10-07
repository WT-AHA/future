package com.aha.common.limit;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 规定固定容量的桶，有水进入，有水流出。对于流进的水我们无法估计进来的数量、速度，对于流出的水我们可以控制速度。
 */
@Data
@Accessors(chain = true)
public class LeakBucket {

    /**
     * 时间
     */
    private long time;
    /**
     * 总量
     */
    private Double total;
    /**
     * 水流出去的速度
     */
    private Double rate;
    /**
     * 当前总量
     */
    private Double nowSize;

    public boolean limit() {
        long now = System.currentTimeMillis();
        nowSize = Math.max(0, (nowSize - (now - time) * rate));
        time = now;
        if ((nowSize + 1) < total) {
            nowSize++;
            return true;
        } else {
            return false;
        }

    }

    public static void main(String[] args) {
        LeakBucket leakBucket = new LeakBucket().setNowSize(0.0).setTotal(2.0).setRate(0.1);
        System.out.println(leakBucket.setTime(System.currentTimeMillis()).limit());
        System.out.println(leakBucket.setTime(System.currentTimeMillis()).limit());
        System.out.println(leakBucket.setTime(System.currentTimeMillis()).limit());
        System.out.println(leakBucket.setTime(System.currentTimeMillis()).limit());
        System.out.println(leakBucket.setTime(System.currentTimeMillis()).limit());
    }

}
