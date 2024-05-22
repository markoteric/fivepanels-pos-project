package Domain.Messenger;

import Domain.User.User;
import Foundation.Assertion.Assertion;
import Foundation.BaseEntity;
import Foundation.Exception.AssertionException;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

public class Messenger extends BaseEntity {

    private LinkedHashSet<Chat> chats;

    public Messenger() {
        this.chats = new LinkedHashSet<>();
    }

    public Messenger(LinkedHashSet<Chat> chats) {
        this.chats = chats;
    }

    public LinkedHashSet<Chat> getChats() {
        return chats;
    }

    public void addChat(Chat chat) {
        Assertion.isNotNull(chat, "chat");
        chats.add(chat);
    }

    public Chat createGroupChat(String groupName, Set<User> members) {
        Assertion.isNotBlank(groupName, "groupName");
        Assertion.isNotNull(members, "members");
        if (members.size() < 3 || members.size() > 20) {
            throw new AssertionException("Group chat must have between 3 and 20 members. Given: " + members.size());
        }
        Chat chat = new Chat(groupName, members);
        addChat(chat);
        return chat;
    }

    public Chat createDirectChat(String groupName, Set<User> members) {
        Assertion.isNotBlank(groupName, "groupName");
        Assertion.isNotNull(members, "members");

        // Check the size of the members set
        if (members.size() != 2) {
            throw new AssertionException("Direct chat must have exactly 2 members. Given: " + members.size());
        }

        Chat chat = new Chat(groupName, members);
        addChat(chat);
        return chat;
    }

    public void sendMessage(UUID chatId, Message message) {
        Assertion.isNotNull(chatId, "chatId");
        Assertion.isNotNull(message, "message");

        // Find the chat with the given ID and add the message
        for (Chat chat : chats) {
            if (chat.getId().equals(chatId)) {
                chat.addMessage(message);
                return;
            }
        }

        throw new AssertionException("Chat not found with ID: " + chatId);
    }

    public void deleteMessage(UUID chatId, UUID messageId) {
        Assertion.isNotNull(chatId, "chatId");
        Assertion.isNotNull(messageId, "messageId");

        for (Chat chat : chats) {
            if (chat.getId().equals(chatId)) {
                chat.removeMessage(messageId);
                return;
            }
        }

        throw new AssertionException("Chat not found with ID: " + chatId);
    }

    public String showMessageHistory(UUID chatId) {

        Assertion.isNotNull(chatId, "chatId");
        for (Chat chat : chats) {

            if (chat.getId().equals(chatId)) {

                return chat.showMessageHistory();
            }
        }

        throw new AssertionException("Chat not found with ID: " + chatId);
    }
}
