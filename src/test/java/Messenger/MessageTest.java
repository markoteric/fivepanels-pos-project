package Messenger;

import static org.junit.jupiter.api.Assertions.*;

import Domain.Exception.ApplicationException;
import Domain.Exception.MessengerException;
import Domain.Media.MediaContent;
import Domain.Messenger.Message;
import Domain.Messenger.Messenger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class MessageTest {

    private Message message;

    @BeforeEach
    void setup() throws MalformedURLException {
        message = new Message();
    }

    @Test
    void test_SetStatus_ShouldFAIL_WhenStatusIsNull() {
        assertThrows(ApplicationException.class, () -> {
            message.setStatus(null);
        });
    }

    @Test
    void test_SetTextContent_ShouldFAIL_WhenTextContentIsNull() {
        assertThrows(ApplicationException.class, () -> {
            message.setTextContent(null);
        });
    }

    @Test
    void test_SetMediaContent_ShouldFAIL_WhenMediaContentIsNull() {
        assertThrows(ApplicationException.class, () -> {
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
