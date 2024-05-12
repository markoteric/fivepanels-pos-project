package Messenger;

import Media.MediaContent;
import Media.TextContent;
import Enum.MessageStatus;
import java.util.List;
import java.util.UUID;

public class Message {
    private UUID id;
    private MessageStatus status;
    private List<TextContent> textContent;
    private List<MediaContent> mediaContent;

}
