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

    public void setProfilePicture(Media profilePicture) {
        // TODO Set profile picture
    }

    public void removeProfilePicture() {
        // TODO Remove profile picture

    }

    public void addSpecialization(Set<Specialization> specializations) {
        // TODO Add specialization

    }


    public void removeSpecialization(Specialization specialization) {
        // TODO Remove specialization
    }

    public void addMedicalTitle(Set<MedicalTitle> medicalTitles) {
        // TODO Add medical title

    }

    public void removeMedicalTitle(MedicalTitle medicalTitle) {

    }

    public void addExperience(Set<Hashtag> experience) {
        // TODO Add experience
    }

    public void removeExperience(Hashtag experience) {
        // TODO Remove experience
    }

    public void addCountry(String country) {
        // TODO Add country
    }

    public void removeCountry(String country) {
        // TODO Remove country
    }

    public void addCity(String city) {
        // TODO Add city
    }

    public void removeCity(String city) {
        // TODO Remove city
    }

    public void addLanguage(Set<Language> languages) {
        // TODO Add language
    }

    public void removeLanguage(Language language) {
        // TODO Remove language
    }

    // ---------------------------------------------------------------------Getters and Setters---------------------------------------------------------------------





}



