package rpcfw.server;

import com.alibaba.fastjson.JSON;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.junit.Test;
import rpcfw.core.RpcRequest;
import rpcfw.core.UserService;

import java.util.concurrent.*;

/**
 * Created by fuwei on 2021/8/4.
 */
public class UtilTest {
    Cache<String, String> cache = CacheBuilder.newBuilder()
            .expireAfterWrite(20, TimeUnit.SECONDS)
            .initialCapacity(50)
            .softValues()
            .build();

    @Test
    public void test() throws InterruptedException {
        cache.put("1", "test1");
        System.out.println(cache.getIfPresent("1"));
        try {
            System.out.println(cache.get("2", () -> "test2"));
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        TimeUnit.SECONDS.sleep(2L);
        System.out.println(cache.getIfPresent("1") + " ," + cache.getIfPresent("2"));
        System.out.printf(" >>> "
                + JSON.toJSONString(new RpcRequest(UserService.class.getName(), "findById", new Object[]{
                Integer.valueOf(1)
        }), true));
    }
}
