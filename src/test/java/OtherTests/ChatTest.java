package OtherTests;

import Domain.Messenger.Chat;
import Domain.Messenger.Message;
import Domain.User.User;
import Domain.User.Misc.Email;
import Domain.User.Misc.Password;
import Foundation.Exception.AssertionException;
import Foundation.Exception.MessengerException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class ChatTest {

    private Chat chat;
    private User user1;
    private User user2;
    private Message textMessage;
    private Message fileMessage;
    private File testFile;

    @BeforeEach
    public void setup() throws Exception {

        user1 = new User("John", "Doe", new Email("user1@example.com"), new Password("foobar123!XD".toCharArray()));
        user2 = new User("Jane", "Doe", new Email("user2@example.com"), new Password("foobar123!XD".toCharArray()));

        Set<User> members = new HashSet<>();
        members.add(user1);
        members.add(user2);

        chat = new Chat("Test Chat", members);

        Path tempFilePath = Files.createTempFile("testfile", ".txt");
        Files.writeString(tempFilePath, "This is a test file.");
        testFile = tempFilePath.toFile();

        textMessage = new Message("Hello, this is a text message!", user1);
        fileMessage = new Message(testFile, user2);

        System.out.println("Text Message ID: " + textMessage.getId());
        System.out.println("File Message ID: " + fileMessage.getId());
    }

    @Test
    public void test_Chat_ShouldCreateChat() {

        assertNotNull(chat.getId());
        assertNotNull(chat.getCreatedAt());
        assertNotNull(chat.getUpdatedAt());
        assertEquals("Test Chat", chat.getName());
        assertEquals(2, chat.getMembers().size());
    }

    @Test
    public void test_Chat_ShouldAddMessage() {

        chat.addMessage(textMessage);
        assertEquals(2, chat.getMessageHistory().size());
        assertTrue(chat.getMessageHistory().contains(textMessage));
    }

    @Test
    public void test_Chat_ShouldDeleteMessage() {

        chat.addMessage(textMessage);
        chat.addMessage(fileMessage);
        chat.removeMessage(textMessage.getId());
        assertEquals(2, chat.getMessageHistory().size());
        assertFalse(chat.getMessageHistory().contains(textMessage));
        assertTrue(chat.getMessageHistory().contains(fileMessage));
    }

    @Test
    public void test_Chat_ShouldShowMessageHistory() throws IOException {
        Chat chat = new Chat("Test Chat", new HashSet<>());
        User user = new User("Test", "User", new Email("test@example.com"), new Password("password123!XDDDKJGSKJGS".toCharArray()));
        Message textMessage = new Message("Hello, this is a text message!", user);

        // Create a temporary file for testing purposes
        File testFile = File.createTempFile("testfile", ".txt");
        testFile.deleteOnExit(); // Ensure the file is deleted after the test
        Message fileMessage = new Message(testFile, user);

        chat.addMessage(textMessage);
        chat.addMessage(fileMessage);

        String history = chat.showMessageHistory();
        assertTrue(history.contains("Hello, this is a text message!"));
        assertTrue(history.contains(testFile.getName()));
    }
    @Test
    public void test_Chat_ShouldThrowException_WhenAddingNullMessage() {

        assertThrows(AssertionException.class, () -> chat.addMessage(null));
    }

    @Test
    public void test_Chat_ShouldThrowException_WhenRemovingNonExistentMessage() {

        UUID fakeMessageId = UUID.randomUUID();
        assertThrows(AssertionException.class, () -> chat.removeMessage(fakeMessageId));
    }

    @Test
    public void test_Chat_ShouldAllowSameContentMessages() {

        Message anotherTextMessage = new Message("Hello, this is a text message!", user1);
        chat.addMessage(textMessage);
        chat.addMessage(anotherTextMessage);
        assertEquals(3, chat.getMessageHistory().size());
    }

    @Test
    public void test_Chat_ShouldNotAllowDuplicateMessageIds() {

        chat.addMessage(textMessage);
        textMessage.setId(UUID.randomUUID());
        assertThrows(MessengerException.class, () -> chat.addMessage(textMessage));
    }
}