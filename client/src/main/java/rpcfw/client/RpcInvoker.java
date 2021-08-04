package rpcfw.client;

import com.alibaba.fastjson.JSON;
import okhttp3.*;
import rpcfw.core.RpcRequest;
import rpcfw.core.RpcResponse;

import java.io.IOException;
import java.lang.reflect.*;

/**
 * Created by fuwei on 2021/8/4.
 */
public class RpcInvoker {
    public static <T> T create(Class<T> clazz, String url) throws IOException, InvocationTargetException, IllegalAccessException {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new CustomHandler(clazz, url));
    }

    public static RpcResponse post(RpcRequest rpcRequest, String url) throws IOException {
        System.out.println(JSON.toJSONString(rpcRequest));
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JSON.toJSONString(rpcRequest));
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        Response response = new OkHttpClient().newCall(request).execute();
        return JSON.parseObject(response.body().string(), RpcResponse.class);
    }


    static class CustomHandler implements InvocationHandler {
        private final Class<?> serviceClass;
        private final String url;

        public <T> CustomHandler(Class<T> clasz, String url) {
            serviceClass = clasz;
            this.url = url;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args)
                throws Throwable {
            RpcRequest rpcRequest = new RpcRequest(serviceClass.getName(), method.getName(), args);
            RpcResponse response = post(rpcRequest, url);
            return JSON.parse(response.getResult().toString());
        }

    }
}
