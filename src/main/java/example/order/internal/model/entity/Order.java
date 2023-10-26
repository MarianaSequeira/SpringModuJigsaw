package example.order.internal.model.entity;

import example.order.internal.model.enums.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

/**
 * @author msequeira
 */

@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    private UUID code;
    private Status status;

    // Add here other relevant fields
}
