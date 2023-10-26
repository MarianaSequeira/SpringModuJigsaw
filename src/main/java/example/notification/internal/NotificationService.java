package example.notification.internal;

import example.notification.INotificationService;
import example.order.OrderDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author msequeira
 */
@Service
public class NotificationService implements INotificationService {
    private static final Logger LOG = LoggerFactory.getLogger(NotificationService.class);

    @Async
    @ApplicationModuleListener
     void notificationEvent(OrderDTO event) throws InterruptedException {
        LOG.info("Let's notify someone of something related to the order {}.", event.code());

        // Let's take our time to send the notification:
        Thread.sleep(1000);

        LOG.info("Someone was definitely notified!");
    }
}
