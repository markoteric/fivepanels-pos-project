package Domain.User;


import Domain.Messenger.Chat;
import Domain.Messenger.Messenger;
import Domain.MedicalCase.MedicalCase;
import Domain.User.Misc.Email;
import Domain.User.Misc.Hashtag;
import Domain.User.Misc.Password;
import Foundation.Assertion.Assertion;
import Foundation.BaseEntity;
import Foundation.Exception.UserException;
import Repository.UserRepository;

import java.io.File;
import java.util.*;

public class User extends BaseEntity {

    private boolean isVerified;
    // Not Null, Not Blank, Has Min Length, Has Max Length, Matches Regex and Validator
    private Email email;
    // Not Null, Not Blank, Has Min Length, Has Max Length, uppercase and lowercase letters, special symbol, number, meets Zxcvbn validator
    private Password password;
    // Not Null
    private Messenger messenger;
    // Not Null
    private Set<MedicalCase> isMemberOfMedicalCases;
    // Not Null
    private Set<MedicalCase> isOwnerOfMedicalCases;
    // Not Null
    private UserProfile userProfile;
    // Not Null
    private Map<UUID, UserRelationship> relationships;
    // Not Null, Not Blank, Has Min Length, Has Max Length, no numbers or special symbols
    private String firstName;
    // Not Null, Not Blank, Has Min Length, Has Max Length, no numbers or special symbols
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
        UserRepository.save(this);
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

    public MedicalCase createNewMedicalCase(String medicalCaseName, List<String> textContent, List<File> fileContent, Set<User> medicalCaseMembers, Set<Hashtag> medicalCaseHashtags) {
        Assertion.isNotNull(medicalCaseName, "medicalCaseName");
        Assertion.isNotBlank(medicalCaseName, "medicalCaseName");
        Assertion.isNotNull(textContent, "textContent");
        Assertion.hasMinLength(medicalCaseName, 8, "medicalCaseName");
        Assertion.hasMaxLength(medicalCaseName, 128, "medicalCaseName");
        // TODO
        MedicalCase mc = new MedicalCase(medicalCaseName, this, textContent, fileContent, medicalCaseMembers, medicalCaseHashtags);
        this.isOwnerOfMedicalCases.add(mc);
        // TODO votes lol

        return mc;
    }

    // How remove?
    public void deleteMedicalCase(MedicalCase medicalCase) {
        Assertion.isNotNull(medicalCase, "medicalCase");
        if (!isOwnerOfMedicalCases.contains(medicalCase)) {
            throw new UserException("User is not an owner of medical case");
        }

        isOwnerOfMedicalCases.remove(medicalCase);;
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
        if (this.getId().equals(userToAdd.getId())) {
            throw new UserException("User cannot add himself to medical case");
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
        Chat chat = new Chat("Direct Chat between " + this + " and " + friend + "!", members);
        this.getMessenger().addChat(chat);
        friend.getMessenger().addChat(chat);
    }

    public void createGroupChatWith(User... friends) {
        Assertion.isNotNull(friends, "friends");
        Assertion.isNotEmpty(friends, "friends");
        if (Arrays.stream(friends).count() < 3 && Arrays.stream(friends).count() > 20) {
            throw new UserException("Group chat must have between 3 and 20 members");
        }
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
        User user3 = new User("Michael", "Johnson", new Email("user3@example.com"), new Password("password123!XDFOOBAR".toCharArray()));

        // Adding friends
        System.out.println(user1.toString() + " adds " + user2.toString() + "!");
        System.out.println(user3.toString() + " adds " + user2.toString() + "!");
        user1.addFriend(user2);
        user3.addFriend(user2);

        // Accepting friend requests
        System.out.println();
        System.out.println(user2.toString() + " accepts " + user1.toString() + "'s friend request!");
        System.out.println(user2.toString() + " accepts " + user3.toString() + "'s friend request!");
        user2.acceptFriendRequest(user1);
        user2.acceptFriendRequest(user3);

        // Printing all chats
        System.out.println();
        System.out.println("Messengers: ");
        System.out.println(user1.getMessenger().getId() + " is John's messenger");
        System.out.println(user2.getMessenger().getId() + " is Jane's messenger");
        System.out.println(user3.getMessenger().getId() + " is Michael's messenger");

        Chat groupChat = new Chat("Group Chat Test", new HashSet<>(Arrays.asList(user1, user2, user3)));
        user1.getMessenger().addChat(groupChat);
        user2.getMessenger().addChat(groupChat);
        user3.getMessenger().addChat(groupChat);

        // Messenger Chat List
        System.out.println();
        System.out.println("Chat List: ");

        System.out.println("John's Chats: " + user1.getMessenger().getChats().toString());
        System.out.println("Jane's Chats: " + user2.getMessenger().getChats().toString());
        System.out.println("Michael's Chats: " + user3.getMessenger().getChats().toString());

        System.out.println("-----------------------------------------------------------");
        System.out.println("MedicalCases:");

        MedicalCase mc = user1.createNewMedicalCase("John's Medical Case", List.of("John's Test Case"), List.of(new File("/resources/cities.txt")), Set.of(user2, user3), Set.of(new Hashtag("#Strabology")));
        System.out.println();
        System.out.println("Information of a test medical case!");
        System.out.println();
        System.out.println(mc.getId() + " is the ID of the medical case!");
        System.out.println("Owner of the medical case: " + mc.getOwner());
        System.out.println("Members of the medical case: " + mc.getMedicalCaseMembers());
        System.out.println();
        System.out.println("Other information of the test medical case!");
        System.out.println();
        System.out.println("Title: " + mc.getMedicalCaseName());
        System.out.println("Description: " + mc.getTextContent());
        System.out.println("Hashtags: " + mc.getMedicalCaseHashtags());
        System.out.println("Files: " + mc.getFileContent());
    }
}
