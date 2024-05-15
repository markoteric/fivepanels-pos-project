package Domain.User;

import Domain.Enum.MedicalCaseStatus;
import Domain.Media.MediaContent;
import Domain.Media.TextContent;
import Domain.MedicalCase.Answer;
import Domain.MedicalCase.MedicalCase;
import Domain.Misc.Assertion;
import Domain.Misc.Email;
import Domain.BaseEntity;
import Domain.Misc.Hashtag;
import Domain.Misc.Password;

import java.net.MalformedURLException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static Domain.Enum.MedicalCaseStatus.UNSOLVED;
import static Domain.Enum.MedicalCaseStatus.SOLVED;
import static Domain.Enum.MedicalCaseStatus.ARCHIVED;
import static Domain.Enum.MedicalCaseStatus.DELETED;

public class User extends BaseEntity {

    // NOT NULL, MIN-LENGTH, MAXLENGTH, @-SYMBOL
    private Email email;
    // DONE IN PASSWORD.JAVA CLASS
    private Password password;
    // NOT NULL
    private UserProfile userProfile;
    private MedicalCase medicalCase;
    // NOT NULL
    private List<UUID> medicalCases;

    public User() {

        super();
        this.email = new Email("paricteric@gmail.com");
        this.password = new Password(new char[5]);
        this.userProfile = new UserProfile();
    }

    public User(String email, char[] password, UserProfile userProfile) {

        super();
        this.email = new Email(email);
        this.password = new Password(password);
        this.userProfile = new UserProfile(userProfile);
    }

    public void addMedicalCase(Set<Hashtag> hashtags, TextContent headline, TextContent content, Set<MediaContent> attachments, MedicalCaseStatus status) throws MalformedURLException {
        // TODO Assertions for hashtags, headline, content, attachment, status
        MedicalCase medicalCase = new MedicalCase();
        medicalCase.setHashtags(hashtags);
        //medicalCase.setHeadline(headline);
        //medicalCase.setContent(content);
        medicalCase.setAttachment((MediaContent) attachments);
        medicalCase.setStatus(UNSOLVED);
    }

    public void removeMedicalCase(UUID id) {
        // TODO Remove medical case
        Assertion.isNotNull(id, "id");
        medicalCases.remove(id);
        medicalCase.setStatus(DELETED);
    }

    public void updateMedicalCase(Set<Hashtag> hashtags, TextContent headline, TextContent content, MediaContent attachment, MedicalCaseStatus status) {
        // TODO Update medical case
        medicalCase.setHashtags(hashtags);
        //medicalCase.setHeadline(headline);
        //medicalCase.setContent(content);
        medicalCase.setAttachment((MediaContent) attachment);
        medicalCase.setStatus(status);
    }


    public void addHashtag(String hashtag) {
        // TODO Add hashtag
        Assertion.isNotNull(hashtag, "hashtag");
        Assertion.isNotBlank(hashtag, "hashtag");
       // medicalCase.addHashtag(hashtag);
    }

    public void removeHashtag(String hashtag) {
        // TODO Remove hashtag
        Assertion.isNotNull(hashtag, "hashtag");
        Assertion.isNotBlank(hashtag, "hashtag");
        medicalCase.getHashtags().remove(hashtag);
    }

    public void addAttachment(MediaContent attachment) {
        // TODO Add attachment
        Assertion.isNotNull(attachment, "attachment");
        //medicalCase.addAttachment((List<MediaContent>) attachment);
    }

    public void removeAttachment(MediaContent attachment) {
        // TODO Remove attachment
        Assertion.isNotNull(attachment, "attachment");
       // medicalCase.removeAttachment((MediaContent) attachment);
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

    public void acceptFriendRequest(UUID id) {
        // TODO Accept friendrequest

    }

    public void declineFriendRequest(UUID id) {
        // TODO Decline friendrequest

    }

    // ______________________________________________________getter and setter____________________________________________________
}

