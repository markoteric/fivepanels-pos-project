package Domain;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public class BaseEntity {

    private UUID id;
    private Instant createdAt;
    private Instant updatedAt;

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity that = (BaseEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
