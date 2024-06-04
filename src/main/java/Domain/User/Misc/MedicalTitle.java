package Domain.User.Misc;

import Foundation.Assertion.Assertion;
import Foundation.Exception.AssertionException;
import Foundation.Exception.MedicalCaseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class MedicalTitle {

    // Not null, be in list of valid titles, maximum 5
    private String medicalTitle;
    // Load from file
    private static Set<String> validTitles;

    public MedicalTitle() {

        this.validTitles = loadValidMedicalTitles();
    }

    public MedicalTitle(String medicalTitle) {

        this();
        setMedicalTitle(medicalTitle);
    }

    public String getMedicalTitle() {

        return medicalTitle;
    }

    public Set<String> loadValidMedicalTitles() {

        Set<String> validTitles = new HashSet<>();
        try (InputStream is = getClass().getResourceAsStream("/medicaltitles.txt")) {

            if (is == null) {

                throw new AssertionException("Resource not found: medicaltitles.txt");
            }

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {

                String title;
                while ((title = reader.readLine()) != null) {
                    validTitles.add(title.trim());
                }

                System.out.println("Loaded " + validTitles.size() + " medical titles.");
            }
        } catch (IOException e) {

            throw new AssertionException("Failed to load medical titles from file: " + e.getMessage());
        }

        return validTitles;
    }

    public static boolean isValidMedicalTitle(String title) {

        return validTitles.contains(title.trim());
    }

    public void setMedicalTitle(String medicalTitle) {

        Assertion.isNotNull(medicalTitle, "medicalTitle");
        Assertion.isNotBlank(medicalTitle, "medicalTitle");
        Assertion.hasMinLength(medicalTitle, 2, "medicalTitle");
        if (!isValidMedicalTitle(medicalTitle)) {

            throw new MedicalCaseException("Invalid medical title: " + medicalTitle);
        }

        this.medicalTitle = medicalTitle;
    }
}
