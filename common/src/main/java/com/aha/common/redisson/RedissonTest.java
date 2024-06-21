package com.aha.common.redisson;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.*;
import org.redisson.client.codec.Codec;
import org.redisson.codec.TypedJsonJacksonCodec;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 大致需求是：
 *   多个副本 控制并发量为 50 (使用 redisson 的 semaphore)
 *   需要实现 普通问答 和 高优先级问答 的 排队机制 (使用 redis 的 list 存储 线程 id 进行排队)
 *   高优先问答 要全部放到 普通问答的最前面，高优先级问答顺序排队 (redis 中 维护一个变量作为游标，标记当前队列中 高优先级的位置，
 *   即下个高优先级插入的位置)
 *
 *   所有请求一起来排队，获取到锁之后判断是否是队列的第一个，如果是获取到资源，反之释放资源，sleep 10 ms 继续排队
 *
 */
@Slf4j
@Component
public class RedissonTest {


    @Bean
    public RedissonClient redissonClient() {

        // 配置
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.150.101:6379")
                .setPassword("123321");
        // 创建RedissonClient对象
        return Redisson.create(config);

    }

    /**
     * @param isHighPriority 是否是高优先级
     */
    public void testLock (boolean isHighPriority) {

        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.150.101:6379")
                .setPassword("123321");
        RedissonClient redissonClient = Redisson.create(config);


        // 应该在项目初始化的时候加载
        RSemaphore semaphore = redissonClient.getSemaphore("Semaphore");
        semaphore.trySetPermits(50);


        // === 锁定资源，保证队列的顺序性 === //
        RLock queueFairLock = redissonClient.getFairLock("llm:queue-fair-lock");
        queueFairLock.lock();
        // 使用一个队列，一个游标，游标的位置标记为重新回答的位置
        long threadId = Thread.currentThread().threadId();

        // 等待队列
        Codec codec = new TypedJsonJacksonCodec(Long.class);
        RList<Long> waitingQueue = redissonClient.getList("llm:waiting-queue", codec);

        // 游标
        RAtomicLong atomicLong = redissonClient.getAtomicLong("llm:high-priority-cursor");

        if (isHighPriority) {

            // 插队
            // 获取游标位置，并将游标加一
            int highPriorityCursor = (int) atomicLong.getAndIncrement();
            waitingQueue.add(highPriorityCursor, threadId);

        } else {
            // 排到队伍的最后面
            waitingQueue.add(threadId);
        }

        queueFairLock.unlock();
        // === 锁定资源，保证队列的顺序性 === //

        try {

            while (true) {

                // 申请资源
                semaphore.acquire();

                // 判断当前的线程id是否与 redis 队列中第一个线程id相同，相同放行，不相同继续等待
                if (Thread.currentThread().threadId() == waitingQueue.getFirst()) {
                    // 队列中删除
                    waitingQueue.removeFirst();
                    break;
                } else {
                    // 释放锁继续等待，延时 10 ms，重新获取锁
                    semaphore.release();
                    Thread.sleep(10);
                }

            }

        } catch (InterruptedException e) {
            semaphore.release();
            if (isHighPriority) {
                // 游标左移
                atomicLong.decrementAndGet();
            }
            log.error(e.getMessage());
        }


        // 业务代码

        // 释放锁
        semaphore.release();
        if (isHighPriority) {
            // 游标左移
            atomicLong.decrementAndGet();
        }

    }


}
