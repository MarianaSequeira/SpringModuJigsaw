package example.order;

import java.util.UUID;

/**
 * @author msequeira
 */
public interface IOrderService {

    OrderDTO findOrderByCode(UUID code);
    OrderDTO createOrder();
}
