package rpcfw.client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import rpcfw.core.UserService;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;


/**
 * Created by fuwei on 2021/8/3.
 */
//@SpringBootApplication
public class ClientApplication {
    static {
        ParserConfig.getGlobalInstance().addAccept("rpcfw.core");
    }
    public static void main(String[] args) throws IllegalAccessException, IOException, InvocationTargetException {
        UserService userService = RpcInvoker.create(UserService.class, "http://localhost:8080/");
        System.out.printf(" >>> " + JSON.toJSONString(userService.findById(10), true));
    }
}
