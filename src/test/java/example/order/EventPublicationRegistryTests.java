package example.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.modulith.test.Scenario;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.modulith.events.core.EventPublicationRegistry;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author msequeira
 */
@ApplicationModuleTest
@RequiredArgsConstructor
@Import(EventPublicationRegistryTests.FailingAsyncTransactionalEventListener.class)
@DirtiesContext
class EventPublicationRegistryTests {

    private final IOrderService orderService;
    private final EventPublicationRegistry registry;
    private final FailingAsyncTransactionalEventListener listener;

    @Test
    void leavesPublicationIncompleteForFailingListener(Scenario scenario) throws Exception {
        scenario.stimulate(orderService::createOrder)
                .andWaitForStateChange(listener::getEx)
                .andVerify(__ -> {
                    assertThat(registry.findIncompletePublications()).hasSize(1);
                });
    }

    @Getter
    static class FailingAsyncTransactionalEventListener {
        Exception ex;

        @ApplicationModuleListener
        void on(OrderDTO event) {
            var exception = new IllegalStateException();

            try {
                throw exception;
            } finally {
                this.ex = exception;
            }
        }
    }
}
