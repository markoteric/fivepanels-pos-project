package Domain.Messenger;

import Domain.BaseEntity;
import Domain.Media.MediaContent;
import Domain.Media.TextContent;
import Domain.Enum.MessageStatus;
import Domain.Misc.Assertion;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class Message extends BaseEntity {

    private MessageStatus status;
    private List<TextContent> textContent;
    private List<MediaContent> mediaContent;

    public Message() {

        super();
        this.status = MessageStatus.SENT;
        setTextContent(textContent);
        setMediaContent(mediaContent);
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

    public String getContent() {

        return textContent.getFirst().getContent();
    }
}
