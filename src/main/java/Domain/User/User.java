package Domain.User;

import Domain.Enum.MedicalCaseStatus;
import Domain.Media.MediaContent;
import Domain.Media.TextContent;
import Domain.Misc.Email;
import Domain.BaseEntity;
import Domain.Misc.Hashtag;
import Domain.Misc.Password;

import java.util.List;

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

    public void addMedicalCase(List<Hashtag> hashtags, TextContent headline, TextContent content, MediaContent attachment) {
        // TODO Assertions for hashtags, headline, content, attachment, status
        MedicalCaseStatus status = UNSOLVED;


    }

    public void removeMedicalCase() {
        // TODO Remove medical case
    }

    public void updateMedicalCase(List<Hashtag> hashtags, TextContent headline, TextContent content, MediaContent attachment, MedicalCaseStatus status) {
        // TODO Update medical case
    }

    public void archiveMedicalCase() {
        // TODO Archive medical case
        // User, MediaCase, und Media (vlt) sind Entities, rest calue
    }

}

