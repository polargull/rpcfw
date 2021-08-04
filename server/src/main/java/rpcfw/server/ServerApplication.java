package rpcfw.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import rpcfw.core.*;

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
    public RpcResponse invoke(@RequestBody RpcRequest request) {
        UserService userService = (UserService) applicationContext.getBean(request.getServiceClass());
        User user = userService.findById((Integer) request.getParams()[0]);
        return new RpcResponse(JSON.toJSONString(user, SerializerFeature.WriteClassName));
    }

    @Bean(name = "rpcfw.core.UserService")
    public UserService createUserService() {
        return new UserServiceImpl();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
