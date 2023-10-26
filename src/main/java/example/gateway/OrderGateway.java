package example.gateway;

import example.order.IOrderService;
import example.order.OrderDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @author msequeira
 */
@RestController
@RequestMapping(value = OrderGateway.BASE_URL)
public class OrderGateway {
    static final String BASE_URL = "/v1/order";

    private final IOrderService orderService;

    public OrderGateway(IOrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{orderCode}")
    public ResponseEntity<OrderDTO> findOrderByCode(@PathVariable UUID orderCode) {
        return ResponseEntity.ok(orderService.findOrderByCode(orderCode));
    }

    /*
     * In a perfect world we would receive information here about the Order we want to create... but you know how it works!
     */
    @PostMapping("/create")
    public ResponseEntity<OrderDTO> createOrder() {
        return ResponseEntity.ok(orderService.createOrder());
    }
}
