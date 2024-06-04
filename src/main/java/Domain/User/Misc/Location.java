package Domain.User.Misc;

import Foundation.Exception.AssertionException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Location {

    // Not null, be in list of valid cities
    private String city;
    // Load from file
    private static Set<String> validCities = new HashSet<>();

    public Location(String city) {

        loadValidCities();
        setCity(city);
    }

    public void loadValidCities() {

        if (validCities.isEmpty()) {

            try (InputStream is = getClass().getResourceAsStream("/cities.txt")) {

                if (is == null) {

                    throw new AssertionException("Resource not found: cities.txt");
                }

                try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {

                    String city;
                    while ((city = reader.readLine()) != null) {

                        validCities.add(city.trim().toLowerCase());
                    }

                    System.out.println("Loaded " + validCities.size() + " cities.");
                }
            } catch (IOException e) {

                throw new AssertionException("Failed to load cities from file" + e.getMessage());
            }
        }
    }

    public boolean isValidCity(String city) {

        return validCities.contains(city.trim().toLowerCase());
    }

    public String getCity() {

        return city;
    }

    public void setCity(String city) {

        if (!isValidCity(city)) {

            throw new AssertionException("Invalid city: " + city);
        }

        this.city = city;
    }
}
