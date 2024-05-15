package Domain.Messenger;

import Domain.Media.MediaContent;
import Domain.Media.TextContent;
import Domain.Enum.MessageStatus;
import Domain.Misc.Assertion;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class Message {
    private UUID id;
    private MessageStatus status;
    private List<TextContent> textContent;
    private List<MediaContent> mediaContent;

    private Instant createdAt;

    private Instant updatedAt;


    public Message() {

        this.id = new UUID(16, 16);
        this.status = MessageStatus.SENT;
        setTextContent(textContent);
        setMediaContent(mediaContent);
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
        this.status = MessageStatus.SENT;
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
}
