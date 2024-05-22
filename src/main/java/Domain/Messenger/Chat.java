package Domain.Messenger;

import Domain.User.User;
import Foundation.Assertion.Assertion;
import Foundation.BaseEntity;
import Foundation.Exception.AssertionException;

import java.time.Instant;
import java.util.*;

public class Chat extends BaseEntity {

    private String name;
    private Set<User> members;
    private List<Message> messageHistory;

    public Chat(String name, Set<User> members) {
        super();
        this.name = name;
        this.members = members;
        this.messageHistory = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Set<User> getMembers() {
        return members;
    }

    public List<Message> getMessageHistory() {
        return new ArrayList<>(messageHistory); // Return a copy to avoid external modification
    }

    public void addMessage(Message message) {
        Assertion.isNotNull(message, "message");
        if (messageHistory.stream().anyMatch(m -> m.getId().equals(message.getId()))) {
            throw new AssertionException("Duplicate message ID: " + message.getId());
        }
        message.setId(UUID.randomUUID());
        message.setUpdatedAt(Instant.now());
        messageHistory.add(message);
    }

    public void removeMessage(UUID messageId) {
        boolean removed = messageHistory.removeIf(message -> message.getId().equals(messageId));
        if (!removed) {
            throw new AssertionException("Message not found with ID: " + messageId);
        }
    }

    public String showMessageHistory() {
        StringBuilder history = new StringBuilder();
        for (Message message : messageHistory) {
            history.append(message.toString()).append("\n");
        }
        return history.toString();
    }
}