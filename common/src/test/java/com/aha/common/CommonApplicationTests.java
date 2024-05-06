package com.aha.common;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class CommonApplicationTests {

    @BeforeAll
    static void init () {
        log.info("init");
    }

    @Test
    public void contextLoads() {
        log.info("test");
    }

}
