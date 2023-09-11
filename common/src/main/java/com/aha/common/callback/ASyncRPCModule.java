package com.aha.common.callback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.Map;


/**
 * 远程调用组件
 */
@Slf4j
public class ASyncRPCModule {

    public void call (String url, Map<String,String> params, CallbackInterface callbackInterface) {

        log.info("远程调用接口地址为：{},参数为：{}", url, params);
        log.info("处理远程调用接口响应数据封装响应体...");
        HashMap<String, Object> data = new HashMap<>();
        data.put("name", "aha");
        data.put("age", 24);
        RPCResponse response = new RPCResponse().setCode(200).setMessage("请求接口成功").setData(data);
        log.info("调用接口完成,执行回调方法...");
        // 重点： 这边调用的是子类实现的 callback 方法
        callbackInterface.callback(response);

    }

}

@Slf4j
class ASyncCustomFrontPage {

    public static void main(String[] args) {
        HashMap<String, String> params = new HashMap<>();
        params.put("name", "aha");
        // 异步回调 -> 最简单的方式便是通过多线程的方式
        new Thread(() ->
            // 模拟前端远程调用的过程 - 查询名字叫 aha 的用户
            new ASyncRPCModule().call("/api/users", params, res -> {

                if (ObjectUtils.isEmpty(res) || ObjectUtils.isEmpty(res.getCode())) {
                    log.error("请求后端接口异常, 响应对象为 null");
                    return;
                }

                // 重点： 这边是 RPCModule 调用上游获取响应信息之后, 回调这个方法的, res 之所以有值是 RPCModule 调用时传递过来的
                if (res.getCode().equals(200)) {
                    log.info("请求后端接口成功,执行成功回调,获得的响应信息为：{},获得的响应数据为：{}", res.getMessage(), res.getData());
                } else {
                    log.error("请求后端接口成功,执行失败回调,错误的响应码为：{}", res.getCode());
                }

            })
        ).start();

        log.info("因为是异步执行,主线程没有阻塞,我在调用接口的下面,但是依然可能是我先执行");

    }

}
