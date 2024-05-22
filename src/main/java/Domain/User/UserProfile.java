package Domain.User;

import Domain.Media.Media;
import Foundation.Assertion.Assertion;
import Domain.User.Misc.Hashtag;
import Domain.User.Misc.Language;
import Domain.Enum.MedicalTitle;
import Domain.Enum.Specialization;

import java.util.Set;

import static Foundation.Assertion.Assertion.isNotBlank;
import static Foundation.Assertion.Assertion.isNotNull;

public class UserProfile {

    private String firstName;
    private String lastName;
    private Media profilePicture;
    private Set<Specialization> specializations;
    private Set<MedicalTitle> medicalTitles;
    private Set<Hashtag> experiences;
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
        this.experiences = userProfile.experiences;
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
        // Method to show default profile picture?
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
        isNotBlank(medicalTitles.toString(), "medicalTitles");
        this.medicalTitles.addAll(medicalTitles);
    }

    public void removeMedicalTitle(MedicalTitle medicalTitle) {
       // Remove medical title
        isNotNull(medicalTitles, "medicalTitles");
        isNotNull(medicalTitle, "medicalTitle");
        isNotBlank(String.valueOf(medicalTitle), "medicalTitle");
        this.medicalTitles.remove(medicalTitle);
    }

    public void addExperience(Set<Hashtag> experiences) {

        isNotNull(experiences, "experiences");
        isNotBlank(experiences.toString(), "experiences");
        this.experiences.addAll(experiences);
    }

    public void removeExperience(Hashtag experience) {

        // TODO Remove experience
        isNotNull(experience, "experience");
        experiences.remove(experience);
        this.experiences = getExperience();
    }

    public void setCountry(String country) {

        isNotNull(country, "country");
        isNotBlank(country, "country");
        this.country = country;
    }

    public void removeCountry() {

        this.country = null;
        this.city = null;
        // Without country -> no city!
    }

    public void setCity(String city) {

        isNotNull(city, "city");
        this.city = city;
    }

    public void removeCity(String city) {

        isNotNull(city, "city");
        isNotBlank(city, "city");
        this.city = null;
    }

    public void addLanguage(Set<Language> languages) {

        Assertion.isNotNull(languages, "languages");
        this.languages.addAll(languages);
    }

    public void removeLanguage(Language language) {
        // TODO Remove language
        isNotNull(language, "language");
        this.languages.remove(language);
        this.languages = getLanguages();
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

        return experiences;
    }

    public void setExperience(Set<Hashtag> experience) {

        this.experiences = experience;
    }

    public String getCountry() {

        return country;
    }

    public String getCity() {

        return city;
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

    public void setFirstName(String name) {

        isNotNull(name, "name");
        isNotBlank(name, "name");
        this.firstName = name;
    }

    public void setLastName(String lastName) {

        isNotNull(lastName, "lastName");
        isNotBlank(lastName, "lastName");
        this.lastName = lastName;
    }
}



