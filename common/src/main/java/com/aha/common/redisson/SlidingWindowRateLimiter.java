package com.aha.common.redisson;

import org.redisson.Redisson;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * 使用 redisson 实现滑动窗口限流
 */
public class SlidingWindowRateLimiter {

    private final RedissonClient redissonClient;

    private static final String LIMIT_KEY_PREFIX = "nft:turbo:limit:";

    public SlidingWindowRateLimiter(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    public Boolean tryAcquire(String key, int limit, int windowSize) {

        /*
         * 使用 redisson 的限流，可以参考下文
         * https://blog.csdn.net/u014401141/article/details/131832362
         * 获取一个限流器
         */
        RRateLimiter rRateLimiter = redissonClient.getRateLimiter(LIMIT_KEY_PREFIX + key);

        if (!rRateLimiter.isExists()) {

            /*
             * RateType.OVERALL 所有的实例共享；RateType.PER_CLIENT 单实例共享 - RateType.OVERALL
             * limit 产生的令牌数                     - 3
             * windowSize 时间周期                   - 5
             * RateIntervalUnit.SECONDS 时间周期的单位 - RateIntervalUnit.SECONDS
             * 意思就是 5s 内产生 3个令牌供所有的实例共享使用
             */
            rRateLimiter.trySetRate(RateType.OVERALL, limit, windowSize, RateIntervalUnit.SECONDS);

        }

        return rRateLimiter.tryAcquire();

    }

    public static void main(String[] args) {

        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.150.101:6379")
                .setPassword("123321");

        SlidingWindowRateLimiter slidingWindowRateLimiter = new SlidingWindowRateLimiter(Redisson.create(config));
        String telephone = "151XXXXXXXX";

        // 短信验证码场景，没分钟只允许给同一个手机好发送一个短信验证码
        Boolean access = slidingWindowRateLimiter.tryAcquire(telephone, 1, 60);
        if (!access) {
            // 错误逻辑
        }
        // 正常发送短信验证码

    }

}
