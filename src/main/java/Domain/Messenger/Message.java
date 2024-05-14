package Domain.Messenger;

import Domain.Media.MediaContent;
import Domain.Media.TextContent;
import Domain.Enum.MessageStatus;
import java.util.List;
import java.util.UUID;

public class Message {
    private UUID id;
    private MessageStatus status;
    private List<TextContent> textContent;
    private List<MediaContent> mediaContent;

}
