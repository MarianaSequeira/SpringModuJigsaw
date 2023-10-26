package example.order.internal;

import example.order.internal.model.entity.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

/**
 * @author msequeira
 */
public interface OrderRepository extends CrudRepository<Order, Long> {
    Order findByCode(UUID code);
}
