package Domain.Messenger;

import Domain.Media.MediaContent;
import Domain.Media.TextContent;
import Domain.Enum.MessageStatus;
import Domain.Misc.Assertion;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Message {
    private UUID id;
    private MessageStatus status;
    private List<TextContent> textContent;
    private List<MediaContent> mediaContent;

    private Instant createdAt;

    private Instant updatedAt;



    public Message() throws MalformedURLException {
      ;

        this.id = new UUID(16, 16);
        this.status = MessageStatus.SENT;
        this.textContent = new ArrayList<>();
        this.textContent.add(new TextContent("First text content"));
        this.textContent.add(new TextContent("Second text content"));
        this.mediaContent = new ArrayList<>();
        this.mediaContent.add(new MediaContent("image/jpeg", "test.jpg", 1024L, new URL("https://www.google.com")));
        this.mediaContent.add(new MediaContent());

        createdAt = Instant.now();
    }

    public UUID getId() {

        return id;
    }

    public void setId(UUID id) {

        // UUID needs to be unique
        this.id = id;
    }

    public MessageStatus getStatus() {

        return status;
    }

    public void setStatus(MessageStatus status) {
        Assertion.isNotNull(status, "status");
        Assertion.isNotBlank(status.toString(), "status");
        this.status = status;
    }


    public List<TextContent> getTextContent() {

        return textContent;
    }

    public void setTextContent(List<TextContent> textContent) {

        Assertion.isNotNull(textContent, "textContent");
        Assertion.isNotBlank(textContent.toString(), "textContent");
        this.textContent = textContent;
    }

    public List<MediaContent> getMediaContent() {

        return mediaContent;
    }

    public void setMediaContent(List<MediaContent> mediaContent) {

        Assertion.isNotNull(mediaContent, "mediaContent");
        Assertion.isNotBlank(mediaContent.toString(), "mediaContent");
        this.mediaContent = mediaContent;
    }

    public String getContent() {
        return textContent.getFirst().getContent();
    }
}
