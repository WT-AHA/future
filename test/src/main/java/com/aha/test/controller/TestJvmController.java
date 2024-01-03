package com.aha.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestJvmController {

    @GetMapping("/test/jvm")
    public String getJvmContext() {
        long maxMemory = Runtime.getRuntime().maxMemory();
        return  "Max memory: " + maxMemory / 1024 /1024 + "MB";
    }

}
