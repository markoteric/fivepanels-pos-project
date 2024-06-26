package Domain.Messenger;

import Domain.User.Misc.Email;
import Domain.User.Misc.Password;
import Domain.User.User;
import Foundation.Assertion.Assertion;
import Foundation.BaseEntity;
import Foundation.Exception.AssertionException;

import java.io.File;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public class Message extends BaseEntity {

    // Not null, not blank, min length 1, max length 1000,
    private String content;
    // Not null, be a file
    private File file;
    // Not null
    private User sender;

    public Message(String content, User sender) {
        super();
        this.setId(UUID.randomUUID());
        setContent(content);
        setSender(sender);
    }

    public Message(File file, User sender) {
        super();
        this.setId(UUID.randomUUID());
        setFile(file);
        setSender(sender);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        Assertion.isNotNull(content, "content");
        Assertion.isNotBlank(content, "content");
        Assertion.hasMinLength(content, 1, "content");
        if (!content.equals(this.content)) {
            this.content = content;
            this.file = null;
            updatedAt = Instant.now();
        }
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
        this.content = null;
        updatedAt = Instant.now();
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        Assertion.isNotNull(sender, "sender");
        this.sender = sender;
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
        return "Message-Content: " + getContent() + " --- Message-File: " + getFile() + " --- Message-Sender: " + getSender();
    }

    public static void main(String[] args) {

        Message textMessage = new Message("Hello", new User("John", "Doe", "New York", new Email("user1@example.com"), new Password("password123!XDFOOBAR".toCharArray())));
        Message fileMessage = new Message(new File("src\\main\\resources\\languages.txt"), new User("Jack", "Doe", "New York", new Email("user1@example.com"), new Password("password123!XDFOOBAR".toCharArray())));

        System.out.println("Printing the text message!");
        System.out.println(textMessage);
        System.out.println();

        System.out.println("Printing the file message!");
        System.out.println(fileMessage);
    }
}
