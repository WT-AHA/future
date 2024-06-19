package com.aha.common.designpatterns.adapter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.Ordered;
import org.springframework.core.log.LogFormatUtils;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.web.context.request.async.AsyncWebRequest;
import org.springframework.web.context.request.async.WebAsyncManager;
import org.springframework.web.context.request.async.WebAsyncUtils;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.function.HandlerFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import java.util.List;

public class HandlerFunctionAdapter implements HandlerAdapter, Ordered {

    private static final Log logger = LogFactory.getLog(org.springframework.web.servlet.function.support.HandlerFunctionAdapter.class);

    private int order = Ordered.LOWEST_PRECEDENCE;

    @Nullable
    private Long asyncRequestTimeout;

    /**
     * Specify the order value for this HandlerAdapter bean.
     * <p>The default value is {@code Ordered.LOWEST_PRECEDENCE}, meaning non-ordered.
     * @see org.springframework.core.Ordered#getOrder()
     */
    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public int getOrder() {
        return this.order;
    }

    /**
     * Specify the amount of time, in milliseconds, before concurrent handling
     * should time out. In Servlet 3, the timeout begins after the main request
     * processing thread has exited and ends when the request is dispatched again
     * for further processing of the concurrently produced result.
     * <p>If this value is not set, the default timeout of the underlying
     * implementation is used.
     * @param timeout the timeout value in milliseconds
     */
    public void setAsyncRequestTimeout(long timeout) {
        this.asyncRequestTimeout = timeout;
    }

    @Override
    public boolean supports(Object handler) {
        return handler instanceof HandlerFunction;
    }

    @Nullable
    @Override
    public ModelAndView handle(HttpServletRequest servletRequest,
                               HttpServletResponse servletResponse,
                               Object handler) throws Exception {

        WebAsyncManager asyncManager = getWebAsyncManager(servletRequest, servletResponse);

        ServerRequest serverRequest = getServerRequest(servletRequest);
        ServerResponse serverResponse;

        if (asyncManager.hasConcurrentResult()) {
            serverResponse = handleAsync(asyncManager);
        }
        else {
            HandlerFunction<?> handlerFunction = (HandlerFunction<?>) handler;
            serverResponse = handlerFunction.handle(serverRequest);
        }

        if (serverResponse != null) {
            return serverResponse.writeTo(servletRequest, servletResponse, new HandlerFunctionAdapter.ServerRequestContext(serverRequest));
        }
        else {
            return null;
        }
    }

    private WebAsyncManager getWebAsyncManager(HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        AsyncWebRequest asyncWebRequest = WebAsyncUtils.createAsyncWebRequest(servletRequest, servletResponse);
        asyncWebRequest.setTimeout(this.asyncRequestTimeout);

        WebAsyncManager asyncManager = WebAsyncUtils.getAsyncManager(servletRequest);
        asyncManager.setAsyncWebRequest(asyncWebRequest);
        return asyncManager;
    }

    private ServerRequest getServerRequest(HttpServletRequest servletRequest) {
        ServerRequest serverRequest =
                (ServerRequest) servletRequest.getAttribute(RouterFunctions.REQUEST_ATTRIBUTE);
        Assert.state(serverRequest != null, () -> "Required attribute '" +
                RouterFunctions.REQUEST_ATTRIBUTE + "' is missing");
        return serverRequest;
    }

    @Nullable
    private ServerResponse handleAsync(WebAsyncManager asyncManager) throws Exception {
        Object result = asyncManager.getConcurrentResult();
        asyncManager.clearConcurrentResult();
        LogFormatUtils.traceDebug(logger, traceOn -> {
            String formatted = LogFormatUtils.formatValue(result, !traceOn);
            return "Resume with async result [" + formatted + "]";
        });
        if (result instanceof ServerResponse) {
            return (ServerResponse) result;
        }
        else if (result instanceof Exception) {
            throw (Exception) result;
        }
        else if (result instanceof Throwable) {
            throw new ServletException("Async processing failed", (Throwable) result);
        }
        else if (result == null) {
            return null;
        }
        else {
            throw new IllegalArgumentException("Unknown result from WebAsyncManager: [" + result + "]");
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public long getLastModified(HttpServletRequest request, Object handler) {
        return -1L;
    }


    private static class ServerRequestContext implements ServerResponse.Context {

        private final ServerRequest serverRequest;


        public ServerRequestContext(ServerRequest serverRequest) {
            this.serverRequest = serverRequest;
        }

        @Override
        public List<HttpMessageConverter<?>> messageConverters() {
            return this.serverRequest.messageConverters();
        }
    }

}
