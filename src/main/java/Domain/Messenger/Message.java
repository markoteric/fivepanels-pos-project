package Domain.Messenger;

import Foundation.Assertion.Assertion;
import Foundation.BaseEntity;
import Foundation.Exception.AssertionException;

import java.io.File;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public class Message extends BaseEntity {

    private String content;
    private File file;

    public Message(String content, File file) {

        super();
        this.setId(UUID.randomUUID());
        setContent(content);
        setFile(file);
    }

    public Message(File file) {

        super();
        this.setId(UUID.randomUUID());
        setFile(file);
    }

    public Message(String content) {

        super();
        this.setId(UUID.randomUUID());
        setContent(content);
    }

    public String getContent() {

        return content;
    }

    public void setContent(String content) {

        Assertion.isNotNull(content, "content");
        Assertion.isNotBlank(content, "content");
        Assertion.hasMinLength(content, 1, "content");
        this.content = content;
        this.file = null;
        updatedAt = Instant.now();
    }

    public File getFile() {

        return file;
    }

    public void setFile(File file) {

        Assertion.isNotNull(file, "file");
        if (!file.exists() || !file.isFile()) {

            throw new AssertionException("File must exist and be a file");
        }

        this.file = file;
        this.content = content;
        updatedAt = Instant.now();
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(getId(), message.getId());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId());
    }

    @Override
    public String toString() {

        return "Message{" +
                "id=" + getId() +
                ", createdAt=" + getCreatedAt() +
                ", updatedAt=" + getUpdatedAt() +
                ", content='" + content + '\'' +
                ", file=" + (file != null ? file.getName() : "null") +
                '}';
    }
}
