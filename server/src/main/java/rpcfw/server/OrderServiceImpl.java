package rpcfw.server;

import org.springframework.stereotype.Service;
import rpcfw.core.Order;
import rpcfw.core.OrderService;

/**
 * Created by fuwei on 2021/8/3.
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Override
    public Order findById(int id) {
        return new Order(id, "order_name" + id);
    }
}
