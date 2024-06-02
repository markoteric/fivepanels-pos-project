package Domain.User;

import Domain.User.Misc.Experience;
import Domain.User.Misc.Hashtag;
import Domain.User.Misc.Language;
import Domain.User.Misc.MedicalTitle;
import Foundation.Assertion.Assertion;

import java.io.File;
import java.util.List;
import java.util.Set;

public class UserProfile {

    private String firstName;
    private String lastName;
    private File profilePicture;
    private List<MedicalTitle> medicalTitles;
    private List<Hashtag> experiences;
    private String city;
    private Set<Language> languages;
    private Integer activityScore;
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

    public UserProfile(String firstName, String lastName, File profilePicture, List<MedicalTitle> medicalTitles, List<Hashtag> experiences, String city, Set<Language> languages, Integer activityScore, Integer experienceScore) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.profilePicture = profilePicture;
        this.medicalTitles = medicalTitles;
        this.experiences = experiences;
        this.city = city;
        this.languages = languages;
        this.activityScore = activityScore;
        this.experienceScore = experienceScore;
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
