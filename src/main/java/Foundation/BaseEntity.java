package Foundation;

import Foundation.Assertion.Assertion;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public abstract class BaseEntity {

    private UUID id;
    private Instant createdAt;
    protected Instant updatedAt;

    public BaseEntity() {

        this.id = UUID.randomUUID();
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    public UUID getId() {

        return id;
    }

    public Instant getCreatedAt() {

        return createdAt;
    }

    public Instant getUpdatedAt() {

        return updatedAt;
    }

    public void setId(UUID id) {

        Assertion.isNotNull(id, "id");
        this.id = id;
    }

    public void setCreatedAt(Instant createdAt) {

        Assertion.isNotNull(createdAt, "createdAt");
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Instant updatedAt) {

        Assertion.isNotNull(updatedAt, "updatedAt");
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity that = (BaseEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, createdAt, updatedAt);
    }
}
