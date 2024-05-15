package Domain.User;

import Domain.Enum.MedicalCaseStatus;
import Domain.Media.MediaContent;
import Domain.Media.TextContent;
import Domain.MedicalCase.Answer;
import Domain.MedicalCase.MedicalCase;
import Domain.Misc.Email;
import Domain.BaseEntity;
import Domain.Misc.Hashtag;
import Domain.Misc.Password;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static Domain.Enum.MedicalCaseStatus.UNSOLVED;
import static Domain.Enum.MedicalCaseStatus.SOLVED;
import static Domain.Enum.MedicalCaseStatus.ARCHIVED;
import static Domain.Enum.MedicalCaseStatus.DELETED;

public class User extends BaseEntity {

    private Email email;
    private Password password;
    private UserProfile userProfile;

    public User() {
        super();
        this.email = new Email("paricteric@gmail.com");
        this.password = new Password("paricteric");
        this.userProfile = new UserProfile();
    }

    public User(String email, String password, UserProfile userProfile) {
        super();
        this.email = new Email(email);
        this.password = new Password(password);
        this.userProfile = new UserProfile(userProfile);
    }

    public void addMedicalCase(Set<Hashtag> hashtags, TextContent headline, TextContent content, MediaContent attachment, MedicalCaseStatus status) {
        // TODO Assertions for hashtags, headline, content, attachment, status

    }

    public void removeMedicalCase() {
        // TODO Remove medical case
    }

    public void updateMedicalCase(List<Hashtag> hashtags, TextContent headline, TextContent content, MediaContent attachment, MedicalCaseStatus status) {
        // TODO Update medical case
    }


    public void addHashtag(List<Hashtag> hashtags) {
        // TODO Add hashtag
    }

    public void removeHashtag() {
        // TODO Remove hashtag
    }

    public void addAttachment(MediaContent attachment) {
        // TODO Add attachment
    }

    public void removeAttachment() {
        // TODO Remove attachment
    }

    public void addMember(UUID id) {
        // TODO Add member
    }

    public void removeMember(UUID id) {
        // TODO Remove member
    }

    public void removeAllMembers() {
        // TODO Remove all members
    }

    public void addAnswer(LinkedHashSet<Answer> answers) {
        // TODO Add answer
    }

    public void removeAnswer(Answer answer) {
        // TODO Remove answer
    }

    public void addFriend(UUID id) {
        // TODO Add friend
    }

    public void removeFriend(UUID id) {
        // TODO Remove friend
    }

    public void acceptFriend(UUID id) {
        // TODO Accept friend

    }

    public void declineFriend(UUID id) {
        // TODO Decline friend

    }

    // ______________________________________________________getter and setter____________________________________________________
}

