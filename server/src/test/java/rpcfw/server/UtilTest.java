package rpcfw.server;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import rpcfw.core.RpcRequest;
import rpcfw.core.UserService;

/**
 * Created by fuwei on 2021/8/4.
 */
public class UtilTest {
    @Test
    public void test() {
        System.out.printf(" >>> "
                + JSON.toJSONString(new RpcRequest(UserService.class.getName(), "findById", new Object[]{
                Integer.valueOf(1)
        }), true));
    }
}
