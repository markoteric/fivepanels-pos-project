package Misc;

import static org.junit.jupiter.api.Assertions.*;

import Domain.Assertion.Assertion;
import Domain.Exception.AssertionException;
import Domain.Messenger.Messenger;
import Domain.Misc.Password;
import Domain.User.UserProfile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PasswordTest {

    private Password password;
    @BeforeEach
    void setup() {
        password = new Password(new char[5]);
    }

    @Test

    void testSetPassword_shouldFail_whenPasswordIsNull() {

        assertThrows(AssertionException.class, () -> {
            password.setPassword(null);
        });
    }


    @Test
    void testSetPassword_shouldFail_whenPasswordIsBlank() {

//        password.setPassword("");
//        assertFalse(password.isValid());

        assertThrows(AssertionException.class, () -> {
            password.setPassword("");
        });

    }

    @Test
    void testSetPassword_shouldFail_whenPasswordIsTooShort() {

//        password.setPassword("1234");
//        assertFalse(password.isValid());

        assertThrows(AssertionException.class, () -> {
            password.setPassword("1234");
        });
    }

    @Test
    void testSetPassword_shouldFail_whenPasswordDoesNotContainNumbers() {

       /* password.setPassword("password");
        assertFalse(password.isValid());*/

        assertThrows(AssertionException.class, () -> {
            password.setPassword("password");
        });
    }
    @Test
    void testSetPassword_shouldFail_whenPasswordDoesNotContainLetters() {
        /*
        password.setPassword("12345");
        assertFalse(password.isValid());*/

        assertThrows(AssertionException.class, () -> {
            password.setPassword("12345");
        });
    }

    @Test
    void testSetPassword_shouldFail_whenPasswordDoesNotContainSymbols() {

//        password.setPassword("12345678Aa");
//        assertFalse(password.isValid());

        assertThrows(AssertionException.class, () -> {
            password.setPassword("12345678Aa");
        });
    }

    @Test
    void testSetPassword_shouldSucceed_whenPasswordMeetsAllCriteria() {

        //password.setPassword("12345678Aa!");
        //assertTrue(password.isValid());

        assertDoesNotThrow(() -> {

            password.setPassword("12345678Aa!");
            assertTrue(password.isValid());
        });
    }
}