package Domain.Messenger;

import Domain.BaseEntity;
import Domain.Enum.MessageStatus;
import Domain.Media.MediaContent;
import Domain.Misc.Assertion;
import Domain.User.UserIdentity;

import javax.swing.*;
import java.util.*;


public class Messenger extends BaseEntity {

    private Set<UserIdentity> members;
    private List<Message> messageHistory;
    private List<Group> groups;

    public Set<UserIdentity> getMembers() {

        return members;
    }

    public void setMembers(Set<UserIdentity> members) {

        Assertion.isNotNull(members, "members");
        Assertion.isNotBlank(members.toString(), "members");
        this.members = members;
    }

    public List<Message> getMessageHistory() {

        return messageHistory;
    }

    public void writeMessage(Message message, MediaContent mediaContent) {

        Assertion.isNotNull(message, "message");
        Assertion.isNotBlank(message.getContent(), "message");
        Message newMessage = new Message();
        newMessage.setId(UUID.randomUUID());
        newMessage.setStatus(MessageStatus.SENT);
        newMessage.setTextContent(message.getTextContent());
        newMessage.setMediaContent(message.getMediaContent());
        messageHistory.add(newMessage);
    }

    public void pinMessage(Message message, UUID messageId) {
        Iterator<Message> iterator = messageHistory.iterator();
        while (iterator.hasNext()) {
            Message currentMessage = iterator.next();
            if (currentMessage.getId().equals(messageId)) {
                currentMessage.setStatus(MessageStatus.PINNED);
                break;
            }
        }
    }

    public String showMessageHistory() {
        List<Message> pinnedMessages = new ArrayList<>();
        List<Message> unpinnedMessages = new ArrayList<>();

        for (Message messages : messageHistory) {
            if (messages.getStatus().equals(MessageStatus.PINNED)) {
                pinnedMessages.add(messages);
            } else {
                unpinnedMessages.add(messages);
            }
        }

        for (Message messages : pinnedMessages) {
            return messages.getContent();
        }

        for (Message message : unpinnedMessages) {
            return message.getContent();
        }
        return null;
    }

    public void deleteMessage(Message message) {
        Iterator<Message> iterator = messageHistory.iterator();
        while (iterator.hasNext()) {
            Message currentMessage = iterator.next();
            if (currentMessage.getId().equals(message.getId())) {
                iterator.remove();
                break;
            }
        }
    }

    public void createGroup(String groupName) {
        Assertion.isNotBlank(groupName, "groupName");
        Assertion.isNotNull(groupName, "groupName");

    }

    public void addMemberToGroup(String groupName, UserIdentity userIdentity) {
        Assertion.isNotBlank(groupName, "groupName");
        Assertion.isNotNull(groupName, "groupName");
        Assertion.isNotBlank(userIdentity.toString(), "userIdentity");
        Assertion.isNotNull(userIdentity, "userIdentity");
    }
}
