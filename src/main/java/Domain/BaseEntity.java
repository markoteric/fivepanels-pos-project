package Domain;

import java.time.Instant;
import java.util.UUID;

public class BaseEntity {
    private UUID id;
    private Instant createdAt;
    private Instant updatedAt;
}
