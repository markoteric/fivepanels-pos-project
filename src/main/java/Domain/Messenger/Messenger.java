package Domain.Messenger;

import Domain.Enum.MessageStatus;
import Domain.Media.MediaContent;
import Domain.Misc.Assertion;
import Domain.User.UserIdentity;

import javax.swing.*;
import java.net.MalformedURLException;
import java.util.*;


public class Messenger {
    private UUID id;
    private Set<UserIdentity> members;
    private List<Message> messageHistory;
    private List<Group> groups;

    public UUID getId() {

        return id;
    }

    public void setId(UUID id) {

        this.id = id;
    }

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

    public void writeMessage(Message message, MediaContent mediaContent) throws MalformedURLException {
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
        Group group = new Group();
        group.setId(UUID.randomUUID());
        group.setName(groupName);
        groups.add(group);
    }

    public void addMemberToGroup(String groupName, Set<UserIdentity> userIdentities, Set<UUID> selectedUUIDs) {
        Assertion.isNotBlank(groupName, "groupName");
        Assertion.isNotNull(groupName, "groupName");
        Assertion.isNotBlank(userIdentities.toString(), "userIdentity");
        Assertion.isNotNull(userIdentities, "userIdentity");
        Assertion.isTrue(!userIdentities.isEmpty(), "No more Users to add to the group");
        Assertion.isNotNull(selectedUUIDs, "selectedUUIDs");

        Group groupToAddMembers = null;
        for (Group group : groups) {
            if (group.getName().equals(groupName)) {
                groupToAddMembers = group;
                break;
            }
        }

        Assertion.isNotNull(groupToAddMembers, "Group '" + groupName + "' not found.");

        for (UserIdentity newUser : userIdentities) {
            Assertion.isTrue(selectedUUIDs.contains(newUser.getUUID()), "User with UUID " + newUser.getUUID() + " not selected.");
            groupToAddMembers.getMembers().add(newUser);
        }
    }





}
