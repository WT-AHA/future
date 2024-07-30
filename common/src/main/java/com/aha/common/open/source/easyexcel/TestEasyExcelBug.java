package com.aha.common.open.source.easyexcel;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.CellExtra;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 复现 easyExcel 的问题
 */
@Slf4j
@RestController
@RequestMapping("/easy/excel")
public class TestEasyExcelBug {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 将制度规章导入到知识库中
     */
    @PostMapping("/file")
    public String importToRegulation (MultipartFile file) {

        try {

            EasyExcelFactory
                    .read(file.getInputStream(), User.class, new UserListener())
                    .sheet().doRead();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return "success";


    }


    @Data
    @Accessors(chain = true)
    public static class User {

        @ExcelProperty(index = 0)
        private String name;
        @ExcelProperty(index = 1)
        private Integer age;

    }


    @Slf4j
    public static class UserListener extends AnalysisEventListener<User> {

        @Override
        public void onException(Exception exception, AnalysisContext context) throws Exception {
            super.onException(exception, context);
        }

        /**
         * 这个每一条数据解析都会来调用
         */
        @Override
        public void invoke(User data, AnalysisContext context) {

            try {
                log.info("解析到一条数据:{}", objectMapper.writeValueAsString(data));
            } catch (JsonProcessingException e) {
                log.error("序列化成 json 失败: {}", e.getMessage());
            }

        }

        @Override
        public void extra(CellExtra extra, AnalysisContext context) {
            super.extra(extra, context);
        }

        @Override
        public void doAfterAllAnalysed(AnalysisContext analysisContext) {

        }

        @Override
        public boolean hasNext(AnalysisContext context) {
            return super.hasNext(context);
        }

    }

}
