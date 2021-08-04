package rpcfw.server;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import rpcfw.core.User;
import rpcfw.core.UserService;

/**
 * Created by fuwei on 2021/8/3.
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public User findById(int id) {
        return new User(id, "name" + id, Lists.newArrayList("test1", "test2"));
    }
}
