package Messenger;

import static org.junit.jupiter.api.Assertions.*;

import Domain.Exception.AssertionException;
import Domain.Media.MediaContent;
import Domain.Messenger.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class MessageTest {

    private Message message;

    @BeforeEach
    void setup() throws MalformedURLException {
        message = new Message();
    }

    @Test
    void test_SetStatus_ShouldFAIL_WhenStatusIsNull() {
        assertThrows(AssertionException.class, () -> {
            message.setStatus(null);
        });
    }

    @Test
    void test_SetTextContent_ShouldFAIL_WhenTextContentIsNull() {
        assertThrows(AssertionException.class, () -> {
            message.setTextContent(null);
        });
    }

    @Test
    void test_SetMediaContent_ShouldFAIL_WhenMediaContentIsNull() {
        assertThrows(AssertionException.class, () -> {
            message.setMediaContent(null);
        });
    }

    @Test
    void test_SetMediaContent_ShouldWork_WhenMediaContentIsNotNull() throws MalformedURLException {
        try {
            MediaContent mediaContent = new MediaContent();
            mediaContent.setContentType("image/jpeg");
            mediaContent.setFileName("test.jpg");
            mediaContent.setFileSize(1024L);
            mediaContent.setUrl(new URL("https://www.google.com"));;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


    }



}
