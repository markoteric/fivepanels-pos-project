package Domain.User;

import Domain.User.Misc.Experience;
import Domain.User.Misc.Hashtag;
import Domain.User.Misc.Language;
import Domain.User.Misc.MedicalTitle;
import Foundation.Assertion.Assertion;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserProfile {

    // Not Null, Not Blank, Has Min Length, Has Max Length, no numbers or special symbols
    private String firstName;
    // Not Null, Not Blank, Has Min Length, Has Max Length, no numbers or special symbols
    private String lastName;
    // Not Null
    private File profilePicture;
    // Not Null, Be in List Of Allowed Values
    private List<MedicalTitle> medicalTitles;
    // Not Null, Must Contain # at beginning
    private List<Hashtag> experiences;
    // Not Null, Be In List Of Allowed Values
    private String city;
    // Not Null
    private Set<Language> languages;
    // Not null, Greater than 0
    private Integer activityScore;
    // Not Null, Greater than 0
    private Integer experienceScore;

    public UserProfile() {

        this.firstName = null;
        this.lastName = null;
        this.profilePicture = null;
        this.medicalTitles = null;
        this.experiences = null;
        this.city = null;
        this.languages = null;
        this.activityScore = 0;
        this.experienceScore = 0;
    }

    public UserProfile(String firstName, String lastName, File profilePicture, List<MedicalTitle> medicalTitles, List<Hashtag> experiences, String city, Set<Language> languages) {
        setFirstName(firstName);
        setLastName(lastName);
        setProfilePicture(profilePicture);
        setMedicalTitle(medicalTitles);
        setExperiences(experiences);
        setCity(city);
        setLanguages(languages);
        setActivityScore(0);
        setExperienceScore(0);
    }

    public UserProfile(String firstName, String lastName, String city) {
        setFirstName(firstName);
        setLastName(lastName);
        this.medicalTitles = new ArrayList<>();
        this.experiences = new ArrayList<>();
        setCity(city);
        this.languages = new HashSet<>();
        setActivityScore(0);
        setExperienceScore(0);
    }

    public String getFirstName() {

        return firstName;
    }

    public void setFirstName(String firstName) {

        Assertion.isNotNull(firstName, "firstName");
        Assertion.isNotBlank(firstName, "firstName");
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {

        Assertion.isNotNull(lastName, "lastName");
        Assertion.isNotBlank(lastName, "lastName");
        this.lastName = lastName;
    }

    public File getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(File profilePicture) {

        Assertion.isNotNull(profilePicture, "profilePicture");
        this.profilePicture = profilePicture;
    }

    public List<MedicalTitle> getMedicalTitle() {

        return medicalTitles;
    }

    public void setMedicalTitle(List<MedicalTitle> medicalTitle) {

        Assertion.isNotNull(medicalTitle, "medicalTitle");
        Assertion.isNotEmpty(medicalTitle, "medicalTitle");
        this.medicalTitles = medicalTitle;
    }

    public List<Hashtag> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<Hashtag> experiences) {

        Assertion.isNotNull(experiences, "experiences");
        Assertion.isNotEmpty(experiences, "experiences");
        Experience.isValidMedicalExperience(experiences.toString());
        this.experiences = experiences;
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

        Assertion.isNotNull(languages, "languages");
        Assertion.isNotEmpty(languages, "languages");
        this.languages = languages;
    }

    public Integer getActivityScore() {
        return activityScore;
    }

    public void setActivityScore(Integer activityScore) {

        Assertion.isNotNull(activityScore, "activityScore");
        this.activityScore = activityScore;
    }

    public Integer getExperienceScore() {
        return experienceScore;
    }

    public void setExperienceScore(Integer experienceScore) {

        Assertion.isNotNull(experienceScore, "experienceScore");
        this.experienceScore = experienceScore;
    }

    public void addMedicalExperience(Hashtag experience) {

        Assertion.isNotNull(experience, "experience");
        Assertion.isNotBlank(experience.toString(), "experience");
        this.experiences.add(experience);
    }

    public void removeMedicalExperience(Hashtag experience) {

        Assertion.isNotNull(experience, "experience");
        Assertion.isNotBlank(experience.toString(), "experience");
        this.experiences.remove(experience);
    }

    public void addLanguage(Language language) {

        Assertion.isNotNull(language, "language");
        Assertion.isNotBlank(language.toString(), "language");
        this.languages.add(language);
    }

    public void removeLanguage(Language language) {

        Assertion.isNotNull(language, "language");
        Assertion.isNotBlank(language.toString(), "language");
        this.languages.remove(language);
    }
}
