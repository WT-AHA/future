package com.aha.common.design.patterns.chainofresponsibility;

/**
 * 责任链 - 处理器接口
 *
 * @author WT
 * date 2021/11/15
 */
public abstract class Handler {

    private Handler next;

    /**
     * 构建责任链并返回下一个处理节点
     *
     * @return the next handler
     */
    public Handler setNext(Handler next) {
        this.next = next;
        return next;
    }

    /**
     * 处理器具体的处理逻辑，交由子类实现
     *
     * @param requestSource 请求时需要携带的资源，用于处理链验证和流转
     * @return 是否处理成功
     */
    public abstract Boolean doHandler(RequestSource requestSource);

    /**
     * 让处理器进行处理，当没有处理器的时候结束处理链
     *
     * @param requestSource 请求时需要携带的资源，用于处理链验证和流转
     * @return 是否处理成功
     */
    protected boolean handleNext(RequestSource requestSource) {

        if (next == null) {
            return true;
        }

        return next.doHandler(requestSource);

    }

}
