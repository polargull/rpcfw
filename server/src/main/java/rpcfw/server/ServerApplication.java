package rpcfw.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.*;
import rpcfw.core.RpcRequest;
import rpcfw.core.RpcResponse;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

/**
 * Created by fuwei on 2021/8/3.
 */
@Slf4j
@RestController
@SpringBootApplication
public class ServerApplication implements ApplicationContextAware {
    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

    private ApplicationContext applicationContext;

    @PostMapping("/")
    public RpcResponse invoke(@RequestBody RpcRequest request) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        Class<?> aClass = Class.forName(request.getServiceClass());
        Map<String, ?> beansOfType = applicationContext.getBeansOfType(aClass);
        Object o = beansOfType.values().toArray()[0];
        Method requestMethod = Arrays.stream(aClass.getMethods()).filter(method -> method.getName().equals(request.getMethod())).findFirst().get();
        Object result = requestMethod.invoke(o, request.getParams());
        RpcResponse rpcResponse = new RpcResponse(JSON.toJSONString(result, SerializerFeature.WriteClassName));
        log.info(">>>{}", JSON.toJSONString(rpcResponse));
        return rpcResponse;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
