package Domain.User;

import Domain.Messenger.Chat;
import Domain.Messenger.Message;
import Domain.Messenger.Messenger;
import Domain.MedicalCase.MedicalCase;
import Domain.User.Misc.Email;
import Domain.User.Misc.Password;
import Foundation.Assertion.Assertion;
import Foundation.BaseEntity;
import Foundation.Exception.UserException;

import java.util.*;

public class User extends BaseEntity {

    private boolean isVerified;
    private Email email;
    private Password password;
    private Messenger messenger;
    private Set<MedicalCase> isMemberOfMedicalCases;
    private Set<MedicalCase> isOwnerOfMedicalCases;
    private UserProfile userProfile;
    private Map<UUID, UserRelationship> relationships;
    private String firstName;
    private String lastName;

    public User(String firstName, String lastName, Email email, Password password) {
        super();
        setEmail(email);
        setPassword(password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.isVerified = false;
        this.messenger = new Messenger();
        this.isMemberOfMedicalCases = new HashSet<>();
        this.isOwnerOfMedicalCases = new HashSet<>();
        this.relationships = new HashMap<>();
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void verify() {
        this.isVerified = true;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        Assertion.isNotNull(email, "email");
        this.email = email;
    }

    public Password getPassword() {
        return password;
    }

    public void setPassword(Password password) {
        Assertion.isNotNull(password, "password");
        this.password = password;
    }

    public Messenger getMessenger() {
        return messenger;
    }

    public Set<MedicalCase> getIsMemberOfMedicalCases() {
        return isMemberOfMedicalCases;
    }

    public Set<MedicalCase> getIsOwnerOfMedicalCases() {
        return isOwnerOfMedicalCases;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        Assertion.isNotNull(userProfile, "userProfile");
        this.userProfile = userProfile;
    }

    public Map<UUID, UserRelationship> getRelationships() {
        return relationships;
    }

    public UserRelationship getUserRelationship(UUID userId) {
        return relationships.get(userId);
    }

    public void setUserRelationship(UUID userId, UserRelationship relationship) {
        relationships.put(userId, relationship);
    }

    public String getFirstName() {

        return firstName;
    }

    public void setFirstName(String firstName) {

        Assertion.isNotNull(firstName, "firstName");
        Assertion.isNotBlank(firstName, "firstName");
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {

        Assertion.isNotNull(lastName, "lastName");
        Assertion.isNotBlank(lastName, "lastName");
        this.lastName = lastName;
    }

    public void createNewMedicalCase(MedicalCase medicalCase) {
        Assertion.isNotNull(medicalCase, "medicalCase");
        this.isOwnerOfMedicalCases.add(medicalCase);
        medicalCase.setOwner(this);
    }

    public void removeMedicalCase(MedicalCase medicalCase) {
        Assertion.isNotNull(medicalCase, "medicalCase");
        if (!isOwnerOfMedicalCases.contains(medicalCase)) {
            throw new UserException("User is not an owner of medical case");
        }
        isOwnerOfMedicalCases.remove(medicalCase);
    }

    public void joinMedicalCase(MedicalCase medicalCase) {
        Assertion.isNotNull(medicalCase, "medicalCase");
        if (this.isMemberOfMedicalCases.contains(medicalCase)) {
            throw new UserException("User is already a member of medical case");
        }
        this.isMemberOfMedicalCases.add(medicalCase);
    }

    public void leaveMedicalCase(MedicalCase medicalCase) {
        Assertion.isNotNull(medicalCase, "medicalCase");
        if (!this.isMemberOfMedicalCases.contains(medicalCase)) {
            throw new UserException("User is not a member of medical case");
        }
        this.isMemberOfMedicalCases.remove(medicalCase);
    }

    public void addMemberToMedicalCase(User userToAdd, MedicalCase medicalCase) {
        Assertion.isNotNull(userToAdd, "userToAdd");
        Assertion.isNotNull(medicalCase, "medicalCase");
        if (medicalCase.getMedicalCaseMembers().contains(userToAdd)) {
            throw new UserException("User is already a member of medical case");
        }
        medicalCase.getMedicalCaseMembers().add(userToAdd);
        userToAdd.getIsMemberOfMedicalCases().add(medicalCase);
    }

    public void removeMemberFromMedicalCase(User userToRemove, MedicalCase medicalCase) {
        Assertion.isNotNull(userToRemove, "userToRemove");
        Assertion.isNotNull(medicalCase, "medicalCase");
        if (!medicalCase.getMedicalCaseMembers().contains(userToRemove)) {
            throw new UserException("User is not a member of medical case");
        }
        medicalCase.getMedicalCaseMembers().remove(userToRemove);
        userToRemove.getIsMemberOfMedicalCases().remove(medicalCase);
    }

    public void joinMedicalCaseAsMember(MedicalCase medicalCase) {
        Assertion.isNotNull(medicalCase, "medicalCase");
        if (this.isMemberOfMedicalCases.contains(medicalCase)) {
            throw new UserException("User is already a member of medical case");
        }
        this.isMemberOfMedicalCases.add(medicalCase);
    }

    public void leaveMedicalCaseAsMember(MedicalCase medicalCase) {
        Assertion.isNotNull(medicalCase, "medicalCase");
        if (!this.isMemberOfMedicalCases.contains(medicalCase)) {
            throw new UserException("User is not a member of medical case");
        }
        this.isMemberOfMedicalCases.remove(medicalCase);
    }

    public void addFriend(User friend) {
        Assertion.isNotNull(friend, "friend");
        if (friend.equals(this)) {
            throw new UserException("Cannot add yourself as a friend");
        }
        this.relationships.put(friend.getId(), UserRelationship.OUTGOING);
        friend.getRelationships().put(this.getId(), UserRelationship.INCOMING);
    }

    public void removeFriend(User friend) {
        Assertion.isNotNull(friend, "friend");
        this.relationships.remove(friend.getId());
        friend.getRelationships().remove(this.getId());
    }

    public void acceptFriendRequest(User friend) {
        Assertion.isNotNull(friend, "friend");
        if (this.relationships.get(friend.getId()) == UserRelationship.INCOMING &&
                friend.getRelationships().get(this.getId()) == UserRelationship.OUTGOING) {
            this.relationships.put(friend.getId(), UserRelationship.ESTABLISHED);
            friend.getRelationships().put(this.getId(), UserRelationship.ESTABLISHED);
            createDirectChat(friend);
        } else {
            throw new UserException("Friend request not found");
        }
    }

    public void declineFriendRequest(User friend) {
        Assertion.isNotNull(friend, "friend");
        if (this.relationships.get(friend.getId()) == UserRelationship.INCOMING) {
            this.relationships.remove(friend.getId());
            friend.getRelationships().remove(this.getId());
        } else {
            throw new UserException("Friend request not found");
        }
    }

    public void createDirectChat(User friend) {
        Set<User> members = new HashSet<>();
        members.add(this);
        members.add(friend);
        Chat chat = new Chat("Direct Chat", members);
        this.getMessenger().addChat(chat);
        friend.getMessenger().addChat(chat);
    }

    public void createGroupChatWith(User... friends) {
        Assertion.isNotNull(friends, "friends");
        Assertion.isNotEmpty(friends, "friends");
        Set<User> members = new HashSet<>();
        members.add(this);
        Collections.addAll(members, friends);
        Chat chat = new Chat("Group Chat", members);
        this.getMessenger().addChat(chat);
        for (User friend : friends) {
            friend.getMessenger().addChat(chat);
        }
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    public static void main(String[] args) {
        // Creating users
        User user1 = new User("John", "Doe", new Email("user1@example.com"), new Password("password123!XDFOOBAR".toCharArray()));
        User user2 = new User("Jane", "Smith", new Email("user2@example.com"), new Password("password123!XDFOOBAR".toCharArray()));
        User user3 = new User("Alice", "Johnson", new Email("user3@example.com"), new Password("password123!XDFOOBAR".toCharArray()));
        User user4 = new User("Bob", "Brown", new Email("user4@example.com"), new Password("password123!XDFOOBAR".toCharArray()));

        // Adding friends and testing relationships
        user1.addFriend(user2);
        System.out.println(user1 + " sent friend request to " + user2);
        displayRelationships(user1, user2, user3, user4);

        user2.acceptFriendRequest(user1);
        System.out.println(user2 + " accepted friend request from " + user1);
        displayRelationships(user1, user2, user3, user4);

        user2.addFriend(user3);
        System.out.println(user2 + " sent friend request to " + user3);
        displayRelationships(user2, user3, user1, user4);

        user3.acceptFriendRequest(user2);
        System.out.println(user3 + " accepted friend request from " + user2);
        displayRelationships(user2, user3, user1, user4);

        user3.addFriend(user4);
        System.out.println(user3 + " sent friend request to " + user4);
        displayRelationships(user3, user4, user1, user2);

        user4.acceptFriendRequest(user3);
        System.out.println(user4 + " accepted friend request from " + user3);
        displayRelationships(user3, user4, user1, user2);

        user4.addFriend(user1);
        System.out.println(user4 + " sent friend request to " + user1);
        displayRelationships(user1, user4, user2, user3);

        user1.declineFriendRequest(user4);
        System.out.println(user1 + " declined friend request from " + user4);
        displayRelationships(user1, user4, user2, user3);

        // Removing friends
        user1.removeFriend(user2);
        System.out.println(user1 + " removed " + user2 + " from friends");
        displayRelationships(user1, user2, user3, user4);

        // Creating chats
        user1.createDirectChat(user3);
        System.out.println(user1 + " created a direct chat with " + user3);

        user2.createGroupChatWith(user3, user4);
        System.out.println(user2 + " created a group chat with " + user3 + " and " + user4);

        // Creating medical cases and managing memberships
        MedicalCase medicalCase1 = new MedicalCase();
        user1.createNewMedicalCase(medicalCase1);
        System.out.println(user1 + " created a new medical case");

        user2.joinMedicalCase(medicalCase1);
        System.out.println(user2 + " joined the medical case created by " + user1);

        user1.addMemberToMedicalCase(user3, medicalCase1);
        System.out.println(user1 + " added " + user3 + " to the medical case");

        user3.leaveMedicalCase(medicalCase1);
        System.out.println(user3 + " left the medical case");

        // Displaying relationships
        displayRelationships(user1, user1, user2, user3, user4);
        displayRelationships(user2, user1, user2, user3, user4);
        displayRelationships(user3, user1, user2, user3, user4);
        displayRelationships(user4, user1, user2, user3, user4);
    }

    private static void displayRelationships(User user, User... allUsers) {
        System.out.println("Relationships of " + user + ":");
        for (Map.Entry<UUID, UserRelationship> entry : user.getRelationships().entrySet()) {
            User relatedUser = Arrays.stream(allUsers).filter(u -> u.getId().equals(entry.getKey())).findFirst().orElse(null);
            if (relatedUser != null) {
                System.out.println(" - " + relatedUser + ": " + entry.getValue());
            } else {
                System.out.println(" - Unknown user with ID: " + entry.getKey() + ": " + entry.getValue());
            }
        }
    }
}
