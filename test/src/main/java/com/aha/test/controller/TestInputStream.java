package com.aha.test.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * http://localhost:8080/test/inputStream?filePath=/Users/wangtong/Downloads/&fileOutputPath=/Users/wangtong/Downloads/temp/
 */
@Slf4j
@RestController
public class TestInputStream {

    @GetMapping("/test/inputStream")
    public String getJvmContext(String filePath, String fileOutputPath) {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        try {
            for (int i=1; i<6; i++) {
                StopWatch stopWatch1 = new StopWatch();
                stopWatch1.start();
//                this.write(new FileInputStream(filePath + "备份_副本" + i + ".zip"), fileOutputPath + "备份_副本_copy" + i + ".zip");
                log.info("备份_副本" + i + ".zip 开始下载");
                this.write(this.get(filePath + "备份_副本" + i + ".zip"), fileOutputPath + "备份_副本_copy" + i + ".zip");
                log.info("备份_副本" + i + ".zip 下载完成");
                stopWatch1.stop();
                log.info("备份_副本" + i + ".zip 耗时: {}", stopWatch1.getTotalTimeMillis());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        stopWatch.stop();
        log.info("总计耗时: {}", stopWatch.getTotalTimeMillis());

        return  "总计耗时: " + stopWatch.getTotalTimeMillis();

    }

    public ByteArrayInputStream get (String downloadPath) throws Exception {

//        InputStream inputStream = Files.newInputStream(Paths.get(downloadPath));
        InputStream inputStream = new BufferedInputStream(new FileInputStream(downloadPath));
        log.info("文件大小：{}", inputStream.available());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int len;
        while ((len = inputStream.read(buffer)) > -1) {
            baos.write(buffer, 0, len);
        }
        baos.flush();
//        return new ByteArrayInputStream(baos.toByteArray());
        ByteArrayInputStream result = new ByteArrayInputStream(baos.toByteArray());
        baos.close();
        return result;

    }


    public boolean write(InputStream inputStream, String fileOutputPath) {

        FileOutputStream fileOutputStream = null;
        try {

            fileOutputStream = new FileOutputStream(fileOutputPath);
            byte[] b=new byte[4096];
            int i;
            while((i=inputStream.read(b))>0){
//                是会一点一点往磁盘写 内存中占用应该只有 byte 数组的大小
//                Thread.sleep(10);
                fileOutputStream.write(b,0,i);
            }
            fileOutputStream.flush();
            return true;

        } catch (Exception e){

            log.error("写文件失败", e);

        } finally {

            try{

                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                // todo 问题1: inputStream 流没有关闭
//                if (inputStream != null) {
//                    inputStream.close();
//                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return false;

    }

}
