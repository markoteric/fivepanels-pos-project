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
    private Email email;
    private Password password;
    private Messenger messenger;
    private Set<MedicalCase> isMemberOfMedicalCases;
    private Set<MedicalCase> isOwnerOfMedicalCases;
    private UserProfile userProfile;
    private Map<UUID, UserRelationship> relationships;

    public User(String firstName, String lastName, String city, Email email, Password password) {
        super();
        setEmail(email);
        setPassword(password);
        this.userProfile = new UserProfile(firstName, lastName, city);
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

    public MedicalCase createNewMedicalCase(String medicalCaseName, List<String> textContent, List<File> fileContent, Set<User> medicalCaseMembers, Set<Hashtag> medicalCaseHashtags) {
        Assertion.isNotNull(medicalCaseName, "medicalCaseName");
        Assertion.isNotBlank(medicalCaseName, "medicalCaseName");
        Assertion.isNotNull(textContent, "textContent");
        Assertion.hasMinLength(medicalCaseName, 8, "medicalCaseName");
        Assertion.hasMaxLength(medicalCaseName, 128, "medicalCaseName");

        MedicalCase mc = new MedicalCase(medicalCaseName, this, textContent, fileContent, medicalCaseMembers, medicalCaseHashtags);
        this.isOwnerOfMedicalCases.add(mc);
        this.userProfile.addActivityScore(10);

        return mc;
    }

    public void deleteMedicalCase(MedicalCase medicalCase) {
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
        this.userProfile.addActivityScore(5);
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

        // Remove relationship
        this.relationships.remove(friend.getId());
        friend.getRelationships().remove(this.getId());

        // Find and remove the chat
        Chat chat = this.messenger.getChats().stream()
                .filter(c -> c.getMembers().contains(this) && c.getMembers().contains(friend))
                .findFirst()
                .orElse(null);

        if (chat != null) {
            this.messenger.removeChat(chat);
            friend.getMessenger().removeChat(chat);
        }
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

    public void addAnswerToMedicalCase(MedicalCase medicalCase, String answerText, boolean isCorrect) {
        Assertion.isNotNull(medicalCase, "medicalCase");
        if (!this.equals(medicalCase.getOwner())) {
            throw new UserException("Only the owner can add answers to the medical case");
        }
        medicalCase.addAnswer(answerText, isCorrect);
    }

    public void voteOnMedicalCase(MedicalCase medicalCase, UUID answerId, int percentage) {
        Assertion.isNotNull(medicalCase, "medicalCase");
        if (!medicalCase.getMedicalCaseMembers().contains(this)) {
            throw new UserException("Only members can vote on the medical case");
        }
        medicalCase.vote(this, answerId, percentage);
        this.userProfile.addActivityScore(5);
    }

    public double getAverageExpertScore() {
        return this.userProfile.getAverageExpertScore();
    }

    public void receiveVoteFeedback(int score, boolean isCorrect) {
        this.userProfile.addExpertScore(score, isCorrect);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId()) &&
                Objects.equals(getEmail(), user.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmail());
    }

    @Override
    public String toString() {
        return userProfile.getFirstName() + " " + userProfile.getLastName();
    }

    public static void main(String[] args) {

        System.out.println("User Relation Test: ");
        // Creating users
        User user1 = new User("John", "Doe", "New York", new Email("user1@example.com"), new Password("password123!XDFOOBAR".toCharArray()));
        User user2 = new User("Jane", "Smith", "New York", new Email("user2@example.com"), new Password("password123!XDFOOBAR".toCharArray()));
        User user3 = new User("Michael", "Johnson", "New York", new Email("user3@example.com"), new Password("password123!XDFOOBAR".toCharArray()));
        User user4 = new User("Sarah", "Williams", "New York", new Email("user4@example.com"), new Password("password123!XDFOOBAR".toCharArray()));

        // Adding friends
        System.out.println(user1.toString() + " adds " + user2.toString() + "!");
        System.out.println(user3.toString() + " adds " + user2.toString() + "!");
        user1.addFriend(user2);
        user3.addFriend(user2);
        user3.addFriend(user4);

        // Accepting friend requests
        System.out.println();
        System.out.println(user2.toString() + " accepts " + user1.toString() + "'s friend request!");
        System.out.println(user2.toString() + " accepts " + user3.toString() + "'s friend request!");
        System.out.println(user4.toString() + " declines " + user3.toString() + "'s friend request!");
        user2.acceptFriendRequest(user1);
        user2.acceptFriendRequest(user3);
        user4.declineFriendRequest(user3);

        // Printing all chats
        System.out.println();
        System.out.println("Messengers: ");
        System.out.println(user1.getMessenger().getId() + " is John's messenger");
        System.out.println(user2.getMessenger().getId() + " is Jane's messenger");
        System.out.println(user3.getMessenger().getId() + " is Michael's messenger");
        System.out.println(user4.getMessenger().getId() + " is Sarah's messenger");

        Chat groupChat = new Chat("Group Chat Test", new HashSet<>(Arrays.asList(user1, user2, user3)));
        user1.getMessenger().addChat(groupChat);
        user2.getMessenger().addChat(groupChat);
        user3.getMessenger().addChat(groupChat);
        user4.getMessenger().addChat(groupChat);
        System.out.println();

        // Get Relations to other users
        System.out.println("Relationships: ");
        System.out.println("John's friends: " + user1.getRelationships());
        System.out.println("Jane's friends: " + user2.getRelationships());
        System.out.println("Michael's friends: " + user3.getRelationships());
        System.out.println("Sarah's friends: " + user4.getRelationships());

        // Removing a friend
        System.out.println();
        System.out.println(user1.toString() + " removes " + user2.toString() + "!");
        user1.removeFriend(user2);
        System.out.println("John's friends: " + user1.getRelationships());
        System.out.println("Jane's friends: " + user2.getRelationships());

        // Messenger Chat List
        System.out.println();
        System.out.println("Chat List: ");

        System.out.println("John's Chats: " + user1.getMessenger().getChats().toString());
        System.out.println("Jane's Chats: " + user2.getMessenger().getChats().toString());
        System.out.println("Michael's Chats: " + user3.getMessenger().getChats().toString());

        System.out.println("-----------------------------------------------------------");
        System.out.println("MedicalCases:");

        MedicalCase mc = user1.createNewMedicalCase("John's Medical Case", List.of("John's Test Case"), List.of(new File("/resources/cities.txt")), Set.of(user2, user3, user4), Set.of(new Hashtag("#Strabology"), new Hashtag("#Health")));
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

        // Adding answers and votes
        System.out.println("-----------------------------------------------------------");
        System.out.println("MedicalCase Votes Test:");

        // Adding answers
        user1.addAnswerToMedicalCase(mc, "Answer A", false);
        user1.addAnswerToMedicalCase(mc, "Answer B", true);

        UUID answerAId = mc.getAnswers().stream().filter(a -> a.getAnswerText().equals("Answer A")).findFirst().get().getId();
        UUID answerBId = mc.getAnswers().stream().filter(a -> a.getAnswerText().equals("Answer B")).findFirst().get().getId();

        // Users voting
        user2.voteOnMedicalCase(mc, answerAId, 70);
        user2.voteOnMedicalCase(mc, answerBId, 30);
        user3.voteOnMedicalCase(mc, answerBId, 20);
        user4.voteOnMedicalCase(mc, answerAId, 50);
        user4.voteOnMedicalCase(mc, answerBId, 50);

        // Displaying live vote results
        System.out.println();
        System.out.println("Live Vote Results:");
        mc.getLiveVoteResults().forEach((answerId, average) -> {
            String answerText = mc.getAnswers().stream().filter(a -> a.getId().equals(answerId)).findFirst().get().getAnswerText();
            System.out.println(answerText + ": " + average + "%");
        });

        // Attempting to exceed voting limit
        System.out.println();
        System.out.println("Attempting to exceed voting limit:");
        try {
            user3.voteOnMedicalCase(mc, answerBId, 88); // This should throw an exception
        } catch (UserException e) {
            System.out.println("Exception: " + e.getMessage());
        }

        // Displaying user scores
        System.out.println();
        System.out.println("User Scores:");
        System.out.println(user1.toString() + " - Activity Score: " + user1.getUserProfile().getActivityScore() + ", Expert Score: " + user1.getUserProfile().getExpertScore() + ", Average Expert Score: " + user1.getAverageExpertScore() + "%");
        System.out.println(user2.toString() + " - Activity Score: " + user2.getUserProfile().getActivityScore() + ", Expert Score: " + user2.getUserProfile().getExpertScore() + ", Average Expert Score: " + user2.getAverageExpertScore() + "%");
        System.out.println(user3.toString() + " - Activity Score: " + user3.getUserProfile().getActivityScore() + ", Expert Score: " + user3.getUserProfile().getExpertScore() + ", Average Expert Score: " + user3.getAverageExpertScore() + "%");
        System.out.println(user4.toString() + " - Activity Score: " + user4.getUserProfile().getActivityScore() + ", Expert Score: " + user4.getUserProfile().getExpertScore() + ", Average Expert Score: " + user4.getAverageExpertScore() + "%");
    }

}
