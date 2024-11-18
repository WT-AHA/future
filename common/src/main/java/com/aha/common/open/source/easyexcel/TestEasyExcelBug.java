package com.aha.common.open.source.easyexcel;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.CellExtra;
import com.alibaba.excel.support.cglib.beans.BeanMap;
import com.alibaba.excel.util.BeanMapUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

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

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, IntrospectionException, InvocationTargetException {
        User user = User.class.newInstance();
        BeanMap beanMap = BeanMapUtils.create(user);
        Object age = beanMap.put("age", 18);
        System.out.println(age);
        System.out.println(user);

//        // 属性描述器：通过API方法获取指定类某个属性的读写方法
//        PropertyDescriptor propertyDescriptor = new PropertyDescriptor("age", User.class);
//        // 获取 user 字节码对象的 id , username 写方法
//        Method writeMethod = propertyDescriptor.getWriteMethod();
//        // 参数1：实例对象 参数2：要设置的值
//        // 给 user 的实例 o 设置 id 或者 username 属性的值
//        // 类似于调用 user.setId user.setUsername; 只不过这里不知道 调用 setId 还是 调用 setUsername,
//        // 所以通过内省的技术，根据列名获取对应属性的 写方法来代替对应属性的 set 方法
//        writeMethod.invoke(user, 18);
//        System.out.println(user);

        Field field = ReflectionUtils.findField(User.class, "age");
        field.setAccessible(true);
        ReflectionUtils.setField(field, user, 18);
        System.out.println(user);

    }

}
