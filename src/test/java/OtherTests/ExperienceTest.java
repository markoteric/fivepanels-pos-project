package OtherTests;

import Domain.User.Misc.Experience;
import Foundation.Exception.AssertionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ExperienceTest {

    private Experience experience;

    @BeforeEach
    public void setup() {

        experience = new Experience("#Cardiology");
    }

    @Test
    public void test_Experience_ShouldThrowException_WhenTitleIsNull() {

        assertThrows(AssertionException.class, () -> new Experience(null));
    }

    @Test
    public void test_Experience_ShouldThrowException_WhenTitleIsBlank() {

        assertThrows(AssertionException.class, () -> new Experience(""));
    }

    @Test
    public void test_Experience_ShouldThrowException_WhenTitleIsTooShort() {

        assertThrows(AssertionException.class, () -> new Experience("M"));
    }

    @Test
    public void test_Experience_ShouldThrowException_WhenTitleIsTooLong() {

        assertThrows(AssertionException.class, () -> new Experience("Msdjhsdgjhkgsdksdgksdjdgskdkgjkksdgksjdjghksdgskgsdgjsdkhgsdkjgsdkgkdgshkjdgsdkgshklkhgdlhkdghkgsd"));
    }

    @Test
    public void test_Experience_ShouldThrowException_WhenTitleIsInvalid() {

        assertThrows(AssertionException.class, () -> new Experience("#INVALID"));
    }

    @Test
    public void test_Experience_ShouldNotThrowException_WhenTitleIsValid() {

        assertDoesNotThrow(() -> new Experience("#Cardiology"));
        assertDoesNotThrow(() -> new Experience("#Dermatology"));
        assertDoesNotThrow(() -> new Experience("#Neurology"));
    }

    @Test
    public void test_isValidMedicalExperience_ShouldReturnTrue_WhenTitleIsValid() {

        assertTrue(Experience.isValidMedicalExperience("#Cardiology"));
        assertTrue(Experience.isValidMedicalExperience("#Dermatology"));
        assertTrue(Experience.isValidMedicalExperience("#Neurology"));
    }

    @Test
    public void test_isValidMedicalExperience_ShouldReturnFalse_WhenTitleIsInvalid() {

        assertFalse(Experience.isValidMedicalExperience("#INVALID"));
        assertFalse(Experience.isValidMedicalExperience("#ABC"));
        assertFalse(Experience.isValidMedicalExperience("#XYZ"));
    }
}
