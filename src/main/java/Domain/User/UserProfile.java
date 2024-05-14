package Domain.User;

import Domain.Media.Media;
import Domain.Misc.Hashtag;
import Domain.Misc.Language;
import Domain.Enum.MedicalTitle;
import Domain.Enum.Specialization;

import java.util.HashSet;
import java.util.Set;

public class UserProfile {


    private Media profilePicture;
    private Set<Specialization> specializations;
    private Set<MedicalTitle> medicalTitles;
    private Set<Hashtag> experience;
    private String country;
    private String city;
    private Set<Language> languages;
    private Integer activityScore;
    private Integer experienceScore;

    public UserProfile() {
        super();
    }


    public UserProfile(UserProfile userProfile) {
        this.profilePicture = userProfile.profilePicture;
        this.specializations = userProfile.specializations;
        this.medicalTitles = userProfile.medicalTitles;
        this.experience = userProfile.experience;
        this.country = userProfile.country;
        this.city = userProfile.city;
        this.languages = userProfile.languages;
        this.activityScore = userProfile.activityScore;
        this.experienceScore = userProfile.experienceScore;
    }

}
