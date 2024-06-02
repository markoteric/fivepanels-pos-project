package Domain.Messenger;

import Domain.User.Misc.Email;
import Domain.User.Misc.Password;
import Domain.User.User;
import Foundation.Assertion.Assertion;
import Foundation.BaseEntity;
import Foundation.Exception.AssertionException;
import Foundation.Exception.MessengerException;

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
        return new ArrayList<>(messageHistory);
    }

    public void addMessage(Message message) {
        Assertion.isNotNull(message, "message");
        if (messageHistory.stream().anyMatch(m -> m.getId().equals(message.getId()))) {
            throw new MessengerException("Duplicate message ID: " + message.getId());
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

    public static void main(String[] args) {
        System.out.println("Group chat lore: ");
        User user1 = new User("John", "Doe", new Email("user1@example.com"), new Password("password123!XDFOOBAR".toCharArray()));
        User user2 = new User("Jane", "Doe", new Email("user2@example.com"), new Password("password123!XDFOOBAR".toCharArray()));
        User user3 = new User("Josh", "Doe", new Email("user3@example.com"), new Password("password123!XDFOOBAR".toCharArray()));
        User user4 = new User("Jack", "Doe", new Email("user4@example.com"), new Password("password123!XDFOOBAR".toCharArray()));

        Set<User> members = new HashSet<>(Arrays.asList(user1, user2, user3));
        Chat chat = new Chat("We love trolling!", members);
        System.out.println(chat.getName());

        chat.addMessage(new Message("TestMessage from user1!", user1));
        chat.addMessage(new Message("TestMessage from user2!", user2));
        chat.addMessage(new Message("TestMessage from user3!", user3));

        System.out.println(chat.showMessageHistory());

        System.out.println("Chat after message deletion:");
        chat.removeMessage(chat.getMessageHistory().get(0).getId());

        System.out.println(chat.showMessageHistory());

        System.out.println("user4 has been added to group chat!");
        chat.getMembers().add(user4);
        chat.addMessage(new Message("Hello world from user4!", user4));

        System.out.println(chat.showMessageHistory());

        chat.addMessage(new Message("Goodybe world from user3!", user3));
        chat.getMembers().remove(user3);
        System.out.println("user3 has been removed from group chat!");

        System.out.println(chat.showMessageHistory());

        System.out.println("Remaining members in chat: ");
        System.out.println(chat.getMembers());
    }
}
