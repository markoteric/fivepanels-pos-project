package OtherTests;

import Domain.User.Misc.Email;
import Foundation.Exception.UserException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EmailTest {

    private Email email;

    @BeforeEach
    public void setup() {

        email = new Email("paricteric@fivepanels.com");
    }

    @Test
    public void test_SetEmail_ShouldThrowException_WhenEmailIsNull() {

        assertThrows(UserException.class, () -> new Email(null));
    }

    @Test
    public void test_SetEmail_ShouldThrowException_WhenEmailIsBlank() {

        assertThrows(UserException.class, () -> new Email(""));
    }

    @Test
    public void test_SetEmail_ShouldThrowException_WhenEmailIsInvalid() {

        assertThrows(UserException.class, () -> new Email("foobar lol"));
    }

    @Test
    public void test_SetEmail_ShouldThrowException_WhenEmailIsTooLong() {

        assertThrows(UserException.class, () -> new Email("foobar@sgksdgjksdjsgjlksgdljklksjgdljksdgljksgdlkjsgdlkjsgdljklsgdjkjksdgljksgdjkdgslkjsgdljksglkjdlkjgsdljksgdlkjsgdlkjsdgljksgdljklsdkjg.com"));
    }

    @Test
    public void test_SetEmail_ShouldThrowException_WhenEmailHasConsecutiveDots() {

        assertThrows(UserException.class, () -> new Email("user..foobar@foobar.com"));
    }

    @Test
    public void test_SetEmail_ShouldThrowException_WhenEmailHasNoTopLevelDomain() {

        assertThrows(UserException.class, () -> new Email("foobar@lol"));
    }

    @Test
    public void test_SetEmail_ShouldThrowException_WhenEmailStartsWithDot() {

        assertThrows(UserException.class, () -> new Email(".foobar@lol.com"));
    }

    @Test
    public void test_SetEmail_ShouldThrowException_WhenEmailEndsWithDot() {

        assertThrows(UserException.class, () -> new Email("foobar.@lol.com"));
    }

    @Test
    public void test_SetEmail_ShouldNotThrowException_WhenEmailHasSpecialCharacters() {

        assertDoesNotThrow(() -> new Email("foobar.foobar+foobar+foobar@foobar.com"));
    }

    @Test
    public void test_SetEmail_ShouldNotThrowException_WhenEmailHasSubdomain() {

        assertDoesNotThrow(() -> new Email("foobar@sub.foobar.com"));
    }

    @Test
    public void test_SetEmail_ShouldReturnTrue_WhenEmailIsValid() {

        assertEquals("paricteric@fivepanels.com", email.getEmail());
    }
}