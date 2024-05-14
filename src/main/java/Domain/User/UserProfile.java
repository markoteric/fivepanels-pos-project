package Domain.User;

import Domain.Media.Media;
import Domain.Misc.Assertion;
import Domain.Misc.Hashtag;
import Domain.Misc.Language;
import Domain.Enum.MedicalTitle;
import Domain.Enum.Specialization;

import java.util.Set;

import static Domain.Misc.Assertion.isNotNull;

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


    private User userProfile;

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
        isNotNull(profilePicture, "profilePicture");
        this.profilePicture = profilePicture;
    }

    public void removeProfilePicture() {
        // TODO Remove profile picture

    }

    public void addSpecialization(Set<Specialization> specializations) {
        isNotNull(specializations, "specializations");
        this.specializations.addAll(specializations);

    }


    public void removeSpecialization(Specialization specialization) {
        // TODO Remove specialization
    }

    public void addMedicalTitle(Set<MedicalTitle> medicalTitles) {
        isNotNull(medicalTitles, "medicalTitles");
        this.medicalTitles.addAll(medicalTitles);
    }

    public void removeMedicalTitle(MedicalTitle medicalTitle) {
       // Remove medical title
    }

    public void addExperience(Set<Hashtag> experience) {
        isNotNull(experience, "experience");
        this.experience.addAll(experience);
    }

    public void removeExperience(Hashtag experience) {
        // TODO Remove experience
    }

    public void addCountry(String country) {
        isNotNull(country, "country");
        this.country = country;
    }

    public void removeCountry(String country) {
        // TODO Remove country
    }

    public void addCity(String city) {
        isNotNull(city, "city");
        this.city = city;
    }

    public void removeCity(String city) {
        // TODO Remove city
    }

    public void addLanguage(Set<Language> languages) {
        Assertion.isNotNull(languages, "languages");
        this.languages.addAll(languages);
    }

    public void removeLanguage(Language language) {
        // TODO Remove language
    }

    // ---------------------------------------------------------------------Getters and Setters---------------------------------------------------------------------


    public Media getProfilePicture() {
        return profilePicture;
    }

    public Set<Specialization> getSpecializations() {
        return specializations;
    }

    public void setSpecializations(Set<Specialization> specializations) {
        this.specializations = specializations;
    }

    public Set<MedicalTitle> getMedicalTitles() {
        return medicalTitles;
    }

    public void setMedicalTitles(Set<MedicalTitle> medicalTitles) {
        this.medicalTitles = medicalTitles;
    }

    public Set<Hashtag> getExperience() {
        return experience;
    }

    public void setExperience(Set<Hashtag> experience) {
        this.experience = experience;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Set<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(Set<Language> languages) {
        this.languages = languages;
    }

    public Integer getActivityScore() {
        return activityScore;
    }

    public void setActivityScore(Integer activityScore) {
        this.activityScore = activityScore;
    }

    public Integer getExperienceScore() {
        return experienceScore;
    }

    public void setExperienceScore(Integer experienceScore) {
        this.experienceScore = experienceScore;
    }

    public User getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(User userProfile) {
        this.userProfile = userProfile;
    }
}



