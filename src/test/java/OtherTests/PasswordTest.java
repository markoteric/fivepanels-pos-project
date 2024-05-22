package OtherTests;

import Domain.User.Misc.Password;
import Foundation.Exception.AssertionException;
import Foundation.Exception.UserException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PasswordTest {

    private Password password;

    @BeforeEach
    public void setup() {

        password = new Password();
    }

    @Test
    public void test_SetPassword_ShouldThrowException_WhenPasswordIsNull() {

        assertThrows(AssertionException.class, () -> password.setPassword(null));
    }

    @Test
    public void test_SetPassword_ShouldThrowException_WhenPasswordIsBlank() {

        assertThrows(AssertionException.class, () -> password.setPassword("".toCharArray()));
    }

    @Test
    public void test_SetPassword_ShouldReturnFalse_WhenPasswordIsNotStrongEnough() {

        assertFalse(password.isPwStrongEnough("password"));
    }

    @Test
    public void test_SetPassword_ShouldThrowException_WhenPasswordIsTooShort() {

        assertThrows(UserException.class, () -> password.setPassword("password".toCharArray()));
    }

    @Test
    public void test_SetPassword_ShouldThrowException_WhenPasswordDoesNotContainNumbers() {

        assertThrows(UserException.class, () -> password.setPassword("password".toCharArray()));
    }

    @Test
    public void test_SetPassword_ShouldThrowException_WhenPasswordDoesNotContainLetters() {

        assertThrows(UserException.class, () -> password.setPassword("12345".toCharArray()));
    }

    @Test
    public void test_SetPassword_ShouldThrowException_WhenPasswordDoesNotContainSpecialCharacters() {

        assertThrows(UserException.class, () -> password.setPassword("password123".toCharArray()));
    }

    @Test
    public void test_SetPassword_ShouldThrowException_WhenPasswordDoesNotHaveMinLength() {

        assertThrows(UserException.class, () -> password.setPassword("1234".toCharArray()));
    }

    @Test
    public void test_SetPassword_ShouldThrowException_WhenPasswordHasMoreCharactersThanMaxLength() {

        assertThrows(AssertionException.class, () -> password.setPassword("1t9wt9w98te9t8ew08wet908twe980ewt08we9800t9ew809e8wt98ewt098wet809we89twe98wte98w9te8098wte098wte098wte098wet098wet098wet098wet908wet908we908t".toCharArray()));
    }

    @Test
    public void test_SetPassword_ShouldReturnTrue_WhenPasswordIsStrongEnough() {

        assertTrue(password.isPwStrongEnough("password123!Axd123"));
    }
}