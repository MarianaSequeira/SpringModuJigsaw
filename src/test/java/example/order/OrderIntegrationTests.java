package example.order;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.modulith.test.AssertablePublishedEvents;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author msequeira
 */
@ApplicationModuleTest
@RequiredArgsConstructor
class OrderIntegrationTests {
    private final IOrderService orderService;

    @Test
    void createOrderCausesEventPublished(AssertablePublishedEvents events) {
        /* Execution */
        OrderDTO order = orderService.createOrder();

        /* Verification */
        assertThat(events).contains(OrderDTO.class).matching(OrderDTO::code, order.code());
    }
}
