package OtherTests;

import Domain.User.Misc.Password;
import Foundation.Exception.AssertionException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class PasswordTest {

    @Test
    public void test_Password_ShouldBeHashed() {
        char[] rawPassword = "StrongPassword123!".toCharArray();
        Password password = new Password(rawPassword);
        assertNotEquals(new String(rawPassword), new String(password.getPassword()), "Password should be hashed");
    }

    @Test
    public void test_Password_ShouldThrowException_WhenPasswordIsNotStrongEnough() {
        char[] weakPassword = "weak".toCharArray();
        assertThrows(AssertionException.class, () -> new Password(weakPassword), "Weak password should throw exception");
    }

    @Test
    public void test_Password_ShouldMeetAllCriteria() {
        char[] validPassword = "ValidPassword123!".toCharArray();
        assertDoesNotThrow(() -> new Password(validPassword), "Valid password should not throw exception");
    }

    @Test
    public void test_Password_ShouldNotBeNull() {
        assertThrows(AssertionException.class, () -> new Password(null), "Password should not be null");
    }

    @Test
    public void test_Password_ShouldThrowException_WhenPasswordIsBlank() {
        char[] blankPassword = "       ".toCharArray();
        assertThrows(AssertionException.class, () -> new Password(blankPassword), "Blank password should throw exception");
    }

    @Test
    public void test_Password_ShouldThrowException_WhenPasswordIsTooShort() {
        char[] shortPassword = "Abc1!".toCharArray();
        assertThrows(AssertionException.class, () -> new Password(shortPassword), "Too short password should throw exception");
    }

    @Test
    public void test_Password_ShouldThrowException_WhenPasswordIsTooLong() {
        char[] longPassword = new char[129];
        Arrays.fill(longPassword, 'A');
        assertThrows(AssertionException.class, () -> new Password(longPassword), "Too long password should throw exception");
    }

    @Test
    public void test_Password_ShouldThrowException_WhenPasswordDoesNotContainLetter() {
        char[] passwordWithoutLetter = "12345678!@#$".toCharArray();
        assertThrows(AssertionException.class, () -> new Password(passwordWithoutLetter), "Password without letter should throw exception");
    }

    @Test
    public void test_Password_ShouldThrowException_WhenPasswordDoesNotContainNumber() {
        char[] passwordWithoutNumber = "Password!@".toCharArray();
        assertThrows(AssertionException.class, () -> new Password(passwordWithoutNumber), "Password without number should throw exception");
    }

    @Test
    public void test_Password_ShouldThrowException_WhenPasswordDoesNotContainSpecialCharacter() {
        char[] passwordWithoutSpecialChar = "Password123".toCharArray();
        assertThrows(AssertionException.class, () -> new Password(passwordWithoutSpecialChar), "Password without special character should throw exception");
    }

    @Test
    public void test_Password_ShouldCreateDefaultPassword() {
        Password defaultPassword = new Password();
        assertNotNull(defaultPassword.getPassword(), "Default password should be set");
        assertTrue(defaultPassword.getPassword().length >= 8, "Default password should meet minimum length");
    }
}
