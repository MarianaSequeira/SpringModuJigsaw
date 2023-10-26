package example.order;

import example.order.internal.model.enums.Status;

import java.util.UUID;

/**
 * @author msequeira
 */
public record OrderDTO(UUID code, Status status) {

    public OrderDTO() {
        this(UUID.randomUUID(), Status.OPEN);
    }
}

