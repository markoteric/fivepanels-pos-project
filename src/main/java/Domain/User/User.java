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








}

