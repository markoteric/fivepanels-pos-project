package Domain.User;

import Domain.Messenger.Chat;
import Domain.Messenger.Messenger;
import Domain.MedicalCase.MedicalCase;
import Domain.User.Misc.Email;
import Domain.User.Misc.Password;
import Foundation.Assertion.Assertion;
import Foundation.BaseEntity;
import Foundation.Exception.UserException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import Domain.User.Social;

public class User extends BaseEntity {

    private boolean isVerified;
    private Email email;
    private Password password;
    private Messenger messenger;
    private Set<MedicalCase> isMemberOfMedicalCases;
    private Set<MedicalCase> isOwnerOfMedicalCases;
    private UserProfile userProfile;
    private Map<UUID, UserRelationship> relationships;


    public User(Email email, Password password) {
        super();
        setEmail(email);
        setPassword(password);
        this.isVerified = false;
        this.messenger = new Messenger();
        this.isMemberOfMedicalCases = new HashSet<>();
        this.isOwnerOfMedicalCases = new HashSet<>();
        this.relationships = new HashMap<>();
    }

    // Getters and Setters //

    public void setEmail(Email email) {
        Assertion.isNotNull(email, "email");
        this.email = email;
    }

    public void setPassword(Password password) {
        Assertion.isNotNull(password, "password");
        this.password = password;
    }

    public void setUserProfile(UserProfile userProfile) {
        Assertion.isNotNull(userProfile, "userProfile");
        this.userProfile = userProfile;
    }

    public void setUserRelationship(UUID userId, UserRelationship relationship) {
        relationships.put(userId, relationship);
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


    public Password getPassword() {
        return password;
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

    public Map<UUID, UserRelationship> getRelationships() {
        return relationships;
    }

    public UserRelationship getUserRelationship(UUID userId) {
        return relationships.get(userId);
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
            createDirectChatWith(friend);
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

    public void createDirectChatWith(User friend) {
        Set<User> members = new HashSet<>();
        members.add(this);
        members.add(friend);
        Chat chat = new Chat("Direct Chat", members);
        this.getMessenger().addChat(chat);
        friend.getMessenger().addChat(chat);
    }









}

