package OtherTests;

import Domain.Messenger.Chat;
import Domain.Messenger.Message;
import Domain.Messenger.Messenger;
import Domain.User.Misc.Email;
import Domain.User.Misc.Password;
import Domain.User.User;
import Foundation.Exception.AssertionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class MessengerTest {

    private Messenger messenger;
    private Chat chat;
    private User user1;
    private User user2;
    private Message textMessage;
    private Message fileMessage;
    private File testFile;

    @BeforeEach
    public void setUp() throws Exception {
        messenger = new Messenger();

        // Initialize users
        user1 = new User("John", "Doe", new Email("user1@example.com"), new Password("password1XD!FOOBARLOL".toCharArray()));
        user2 = new User("Jane", "Doe", new Email("user2@example.com"), new Password("password2XD!FOOBARLOL".toCharArray()));

        Set<User> members = new HashSet<>();
        members.add(user1);
        members.add(user2);

        chat = new Chat("Test Chat", members);

        // Create a temporary file for testing
        Path tempFilePath = Files.createTempFile("testfile", ".txt");
        Files.writeString(tempFilePath, "This is a test file.");
        testFile = tempFilePath.toFile();

        // Initialize messages
        textMessage = new Message("Hello, this is a text message!", user1);
        fileMessage = new Message(testFile, user2);

        // Add chat to messenger
        messenger.addChat(chat);
    }

    @Test
    public void test_Messenger_ShouldAddChat() {
        assertEquals(1, messenger.getChats().size());
        assertTrue(messenger.getChats().contains(chat));
    }

    @Test
    public void test_Messenger_ShouldSendMessage() {

        messenger.sendMessage(chat.getId(), textMessage);
        assertEquals(1, chat.getMessageHistory().size());
        assertTrue(chat.getMessageHistory().contains(textMessage));
    }

    @Test
    public void test_Messenger_ShouldDeleteMessage() {

        messenger.sendMessage(chat.getId(), textMessage);
        messenger.sendMessage(chat.getId(), fileMessage);
        messenger.deleteMessage(chat.getId(), textMessage.getId());
        assertEquals(1, chat.getMessageHistory().size());
        assertFalse(chat.getMessageHistory().contains(textMessage));
        assertTrue(chat.getMessageHistory().contains(fileMessage));
    }

    @Test
    public void test_Messenger_ShouldShowMessageHistory() {
        messenger.sendMessage(chat.getId(), textMessage);
        messenger.sendMessage(chat.getId(), fileMessage);
        String history = messenger.showMessageHistory(chat.getId());
        assertTrue(history.contains("Hello, this is a text message!"));
        assertTrue(history.contains(testFile.getName()));
    }

    @Test
    public void test_Messenger_ShouldThrowException_WhenAddingNullChat() {
        assertThrows(AssertionException.class, () -> messenger.addChat(null));
    }

    @Test
    public void test_Messenger_ShouldThrowException_WhenSendingNullMessage() {
        assertThrows(AssertionException.class, () -> messenger.sendMessage(chat.getId(), null));
    }

    @Test
    public void test_Messenger_ShouldThrowException_WhenChatNotFound() {
        UUID fakeChatId = UUID.randomUUID();
        assertThrows(AssertionException.class, () -> messenger.sendMessage(fakeChatId, textMessage));
    }

    @Test
    public void test_Messenger_ShouldThrowException_WhenMessageNotFoundForDeletion() {
        UUID fakeMessageId = UUID.randomUUID();
        assertThrows(AssertionException.class, () -> messenger.deleteMessage(chat.getId(), fakeMessageId));
    }
}
