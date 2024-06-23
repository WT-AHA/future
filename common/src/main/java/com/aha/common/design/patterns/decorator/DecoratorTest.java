package com.aha.common.design.patterns.decorator;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * 装饰者模式测试
 *
 * 饰者模式可以动态地给对象添加一些额外的属性或行为。
 * 相比于使用继承，装饰者模式更加灵活。
 * 简单点儿说就是当我们需要修改原有的功能，但我们又不愿直接去修改原有的代码时，设计一个 Decorator 套在原有代码外面。
 * 其实在 JDK 中就有很多地方用到了装饰者模式，
 * 比如 InputStream家族，InputStream 类下有 FileInputStream (读取文件)、
 * BufferedInputStream (增加缓存,使读取文件速度大大提升)等子类都在不修改InputStream 代码的情况下扩展了它的功能。
 */
@Slf4j
public class DecoratorTest {

    public static void main(String[] args) {

        try (
                // 创建一个基本的文件输入流
                InputStream fileInputStream = new FileInputStream("example.txt");
                // 使用DataInputStream装饰器，以便按特定格式读取数据
                InputStream dataInputStream = new DataInputStream(fileInputStream);
                // 使用BufferedInputStream装饰器，以便进行缓冲读取
                InputStream bufferedInputStream = new BufferedInputStream(dataInputStream);
        ) {

            // 读取数据
            int value = bufferedInputStream.read();
            System.out.println("读取到的值: " + value);

        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

    }

}
