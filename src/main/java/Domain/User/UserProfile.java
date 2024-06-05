package Domain.User;

import Domain.User.Misc.Experience;
import Domain.User.Misc.Hashtag;
import Domain.User.Misc.Language;
import Domain.User.Misc.MedicalTitle;
import Foundation.Assertion.Assertion;

import java.io.File;
import java.util.*;

public class UserProfile {

    private String firstName;
    private String lastName;
    private File profilePicture;
    private List<MedicalTitle> medicalTitles;
    private List<Hashtag> experiences;
    private String city;
    private Set<Language> languages;
    private Integer activityScore;
    private Integer expertScore;
    private Double averageExpertScore;
    private int correctAnswersCount;

    public UserProfile() {
        this.firstName = null;
        this.lastName = null;
        this.profilePicture = null;
        this.medicalTitles = null;
        this.experiences = null;
        this.city = null;
        this.languages = null;
        this.activityScore = 0;
        this.expertScore = 0;
        this.averageExpertScore = 0.0;
        this.correctAnswersCount = 0;
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
        setExpertScore(0);
        setAverageExpertScore(0.0);
        this.correctAnswersCount = 0;
    }

    public UserProfile(String firstName, String lastName, String city) {
        setFirstName(firstName);
        setLastName(lastName);
        this.medicalTitles = new ArrayList<>();
        this.experiences = new ArrayList<>();
        setCity(city);
        this.languages = new HashSet<>();
        setActivityScore(0);
        setExpertScore(0);
        setAverageExpertScore(0.0);
        this.correctAnswersCount = 0;
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
        this.activityScore = activityScore;
    }

    public Integer getExpertScore() {
        return expertScore;
    }

    public void setExpertScore(Integer expertScore) {
        this.expertScore = expertScore;
    }

    public Double getAverageExpertScore() {
        return averageExpertScore;
    }

    public void setAverageExpertScore(Double averageExpertScore) {
        this.averageExpertScore = averageExpertScore;
    }

    public void addExpertScore(int percentage, boolean correct) {
        if (correct) {
            this.correctAnswersCount++;
            this.expertScore += percentage;
            updateAverageExpertScore();
        }
    }

    private void updateAverageExpertScore() {
        this.averageExpertScore = correctAnswersCount > 0 ? (double) expertScore / correctAnswersCount : 0.0;
    }

    public void addActivityScore(Integer activityScoreToAdd) {
        this.activityScore += activityScoreToAdd;
    }
}
