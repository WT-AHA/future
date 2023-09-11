package com.aha.common.callback;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * 模拟前端调用接口组件来讲解回调的过程
 *
 * @author WT
 * date 2021/11/10
 */

interface CallbackInterface {

    /**
     * 远程调用接口之后回调的方法
     */
    void callback(RPCResponse rpcResponse);

}

/**
 * 调用接口之后返回的对象
 */
@Data
@Accessors(chain = true)
class RPCResponse {

    private Integer code;
    private String message;
    private Map<String, Object> data;

}

/**
 * 远程调用组件
 */
@Slf4j
public class RPCModule {

    public void call (String url, Map<String,String> params, CallbackInterface callbackInterface) {
        log.info("远程调用接口地址为：{},参数为：{}",url,params);
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
class CustomFrontPage {

    public static void main(String[] args) {
        HashMap<String, String> params = new HashMap<>();
        params.put("name", "aha");
        // 模拟前端远程调用的过程 - 查询名字叫 aha 的用户
        new RPCModule().call("/api/users", params, res -> {
            // 重点： 这边是 RPCModule 调用上游获取响应信息之后, 回调这个方法的, res 之所以有值是 RPCModule 调用 call 方法时传递过来的
            if (res.getCode().equals(200)) {
                log.info("请求后端接口成功,执行成功回调,获得的响应信息为：{},获得的响应数据为：{}", res.getMessage(), res.getData());
            } else {
                log.error("请求后端接口成功,执行失败回调,错误的响应码为：{}", res.getCode());
            }
        });
    }

}
