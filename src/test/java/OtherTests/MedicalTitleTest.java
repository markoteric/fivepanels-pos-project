package OtherTests;

import Domain.User.Misc.MedicalTitle;
import Foundation.Exception.AssertionException;
import Foundation.Exception.UserException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MedicalTitleTest {

    @Test
    public void test_MedicalTitle_ShouldThrowException_WhenTitleIsNull() {

        assertThrows(AssertionException.class, () -> new MedicalTitle(null));
    }

    @Test
    public void test_MedicalTitle_ShouldThrowException_WhenTitleIsBlank() {

        assertThrows(AssertionException.class, () -> new MedicalTitle(""));
    }

    @Test
    public void test_MedicalTitle_ShouldThrowException_WhenTitleIsTooShort() {

        assertThrows(AssertionException.class, () -> new MedicalTitle("M"));
    }

    @Test
    public void test_MedicalTitle_ShouldThrowException_WhenTitleIsInvalid() {

        assertThrows(UserException.class, () -> new MedicalTitle("INVALID"));
    }

    @Test
    public void test_isValidMedicalTitle_ShouldReturnFalse_WhenTitleIsInvalid() {

        assertFalse(MedicalTitle.isValidMedicalTitle("INVALID"));
        assertFalse(MedicalTitle.isValidMedicalTitle("ABC"));
        assertFalse(MedicalTitle.isValidMedicalTitle("XYZ"));
        assertFalse(MedicalTitle.isValidMedicalTitle("123"));
    }

    @Test
    public void test_MedicalTitle_ShouldNotThrowException_WhenTitleIsValid() {

        assertDoesNotThrow(() -> new MedicalTitle("MD"));
        assertDoesNotThrow(() -> new MedicalTitle("DO"));
        assertDoesNotThrow(() -> new MedicalTitle("PharmD"));
        assertDoesNotThrow(() -> new MedicalTitle("DPT"));
        assertDoesNotThrow(() -> new MedicalTitle("DrPH"));
    }

    @Test
    public void test_isValidMedicalTitle_ShouldReturnTrue_WhenTitleIsValid() {

        assertTrue(MedicalTitle.isValidMedicalTitle("MD"));
        assertTrue(MedicalTitle.isValidMedicalTitle("DO"));
        assertTrue(MedicalTitle.isValidMedicalTitle("PharmD"));
        assertTrue(MedicalTitle.isValidMedicalTitle("DPT"));
        assertTrue(MedicalTitle.isValidMedicalTitle("DrPH"));
    }
}
