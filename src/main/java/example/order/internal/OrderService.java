package example.order.internal;

import example.order.OrderDTO;
import example.order.IOrderService;
import example.order.internal.model.entity.Order;
import example.order.internal.model.enums.Status;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author msequeira
 */
@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {

    private static final Logger LOG = LoggerFactory.getLogger(OrderService.class);

    private final OrderRepository repository;
    private final ApplicationEventPublisher events;

    @Override
    public OrderDTO findOrderByCode(UUID orderCode) {
        Order order = repository.findByCode(orderCode);
        return new OrderDTO(order.getCode(), order.getStatus());
    }

    @Override
    @Transactional
    public OrderDTO createOrder() {
        Order order = new Order(UUID.randomUUID(), Status.OPEN);
        LOG.info("About to create and store our order {}!", order.getCode());
        repository.save(order);

        // We're announcing through events that a new order is in place:
        OrderDTO orderDTO = new OrderDTO(order.getCode(), order.getStatus());
        events.publishEvent(orderDTO);

        return orderDTO;
    }
}
