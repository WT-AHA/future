package com.aha.test.controller;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class XmlUtils {

    private static final XStream xStreamHelper = new XStream(new StaxDriver());
    // 初始化配置
    static {
        XStream.setupDefaultSecurity(xStreamHelper);
        xStreamHelper.allowTypesByWildcard(new String[]{"org.demo.springcloud.**"});
        xStreamHelper.ignoreUnknownElements();//忽略未知节点
        xStreamHelper.autodetectAnnotations(true);
    }

    public static <T> T xmlToBean(String xmlStr, Class<?> clazz) {

        // 转化权限相关问题
        xStreamHelper.addPermission(AnyTypePermission.ANY);
//        XStream.setupDefaultSecurity(xStreamHelper);
        xStreamHelper.ignoreUnknownElements();//忽略未知节点
        xStreamHelper.autodetectAnnotations(true);
        xStreamHelper.processAnnotations(clazz);
        return (T) xStreamHelper.fromXML(xmlStr);
    }

    public static <T> T xmlToBean(File xmlFile, Class<?> clazz) {
        xStreamHelper.processAnnotations(clazz);
        return (T) xStreamHelper.fromXML(xmlFile);
    }

    public static <T> String beanToXmlStr(T t) {
        return xStreamHelper.toXML(t);
    }

    public static <T> boolean beanToXml(T t, String filePath){
        boolean flag = true;
        try {
            FileWriter fileWriter = new FileWriter(new File(filePath));
            fileWriter.write(xStreamHelper.toXML(t));
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            flag = false;
            e.printStackTrace();
        }finally {
            return flag;
        }
    }

    public static void main(String[] args) {
        BsBankInfo bsBankInfo = new BsBankInfo();
        bsBankInfo.setBankName("中国建设银行");
        bsBankInfo.setBankNo("b10001");
        BsBankInfo.BankInfo bankInfo = new BsBankInfo.BankInfo();
        bankInfo.setInfo("text");
        bsBankInfo.setBankInfo(bankInfo);

        String xmlStr = beanToXmlStr(bsBankInfo);
        System.out.println("===>" + xmlStr);
        System.out.println(xmlToBean(xmlStr, BsBankInfo.class).toString());
//        System.out.println(beanToXml(bsBankInfo, "d:/t.xml"));
    }

}
