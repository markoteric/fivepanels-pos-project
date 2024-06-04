package Domain.User.Misc;

import Foundation.Assertion.Assertion;
import Foundation.Exception.AssertionException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Experience {


    // Not null, be in list of valid experiences
    private String experience;
    // Load from file
    private static final Set<String> validExperiences = new HashSet<>();

    static {
        loadValidMedicalExperiences();
    }

    public Experience(String experience) {
        setExperience(experience);
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        Assertion.isNotNull(experience, "experience");
        Assertion.isNotBlank(experience, "experience");
        Assertion.hasMinLength(experience, 2, "experience");
        if (!isValidMedicalExperience(experience)) {
            throw new AssertionException("Invalid medical experience: " + experience);
        }
        this.experience = experience;
    }

    private static void loadValidMedicalExperiences() {

        try (InputStream is = Experience.class.getResourceAsStream("/medicalexperiences.txt")) {

            if (is == null) {

                throw new AssertionException("Resource not found: medicalexperiences.txt");
            }

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {

                String title;
                while ((title = reader.readLine()) != null) {
                    validExperiences.add("#" + title.trim());
                }

                System.out.println("Loaded " + validExperiences.size() + " medical experiences.");
            }
        } catch (IOException e) {

            throw new AssertionException("Failed to load medical experiences from file: " + e.getMessage());
        }
    }

    public static boolean isValidMedicalExperience(String experience) {
        return validExperiences.contains(experience.trim());
    }
}
