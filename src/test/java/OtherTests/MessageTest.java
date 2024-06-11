package OtherTests;

import Domain.Messenger.Message;
import Domain.User.Misc.Email;
import Domain.User.Misc.Password;
import Domain.User.User;
import Foundation.Exception.AssertionException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    private User sender = new User("John", "Doe", "New York", new Email("john.doe@example.com"), new Password("password123!XDLOLFWDGSKJGSD".toCharArray()));

    @Test
    public void test_Message_ShouldCreateValidMessage() {
        Message message = new Message("Hello, World!", sender);
        assertEquals("Hello, World!", message.getContent());
        assertEquals(sender, message.getSender());
    }

    @Test
    public void test_Message_ShouldThrowException_WhenContentIsNull() {
        assertThrows(AssertionException.class, () -> new Message((String) null, sender));
    }

    @Test
    public void test_Message_ShouldThrowException_WhenContentIsBlank() {
        assertThrows(AssertionException.class, () -> new Message("", sender));
    }

    @Test
    public void test_Message_ShouldStoreTimestamps() {
        Message message = new Message("Hello, World!", sender);
        assertNotNull(message.getCreatedAt());
        assertNotNull(message.getUpdatedAt());
        assertEquals(message.getCreatedAt(), message.getUpdatedAt());
    }

    @Test
    public void test_Message_ShouldUpdateContent() {
        Message message = new Message("Initial Content", sender);
        message.setContent("Updated Content");
        assertEquals("Updated Content", message.getContent());
    }

    @Test
    public void test_Message_ShouldThrowException_WhenUpdatingContentToNull() {
        Message message = new Message("Initial Content", sender);
        assertThrows(AssertionException.class, () -> message.setContent(null));
    }

    @Test
    public void test_Message_ShouldThrowException_WhenUpdatingContentToBlank() {
        Message message = new Message("Initial Content", sender);
        assertThrows(AssertionException.class, () -> message.setContent(""));
    }

    @Test
    public void test_Message_ShouldUpdateTimestampWhenContentIsUpdated() throws InterruptedException {
        Message message = new Message("Initial Content", sender);
        Instant oldUpdatedAt = message.getUpdatedAt();

        // Adding a slight delay to ensure different timestamps
        Thread.sleep(10);

        message.setContent("Updated Content");
        Instant newUpdatedAt = message.getUpdatedAt();

        assertTrue(newUpdatedAt.isAfter(oldUpdatedAt));
    }

    @Test
    public void test_Message_ShouldNotUpdateTimestampWhenContentIsSame() throws InterruptedException {
        Message message = new Message("Initial Content", sender);
        Instant initialUpdatedAt = message.getUpdatedAt();

        // Adding a slight delay to ensure different timestamps
        Thread.sleep(10);

        message.setContent("Initial Content");
        Instant afterUpdatedAt = message.getUpdatedAt();

        assertEquals(initialUpdatedAt, afterUpdatedAt);
    }

    @Test
    public void test_Message_ShouldCreateValidFileMessage() {
        File file = new File("testfile.txt");
        try {
            // Create a test file for the test
            assertTrue(file.createNewFile());
        } catch (Exception e) {
            e.printStackTrace();
            fail("Failed to create test file.");
        }

        Message message = new Message(file, sender);
        assertEquals(file, message.getFile());
        assertNull(message.getContent());
        assertEquals(sender, message.getSender());

        // Clean up the test file
        file.delete();
    }

    @Test
    public void test_Message_ShouldThrowException_WhenFileIsNull() {
        assertThrows(AssertionException.class, () -> new Message((File) null, sender));
    }

    @Test
    public void test_Message_ShouldThrowException_WhenFileDoesNotExist() {
        File nonExistentFile = new File("nonexistent.txt");
        assertThrows(AssertionException.class, () -> new Message(nonExistentFile, sender));
    }
}
