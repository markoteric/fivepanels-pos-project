package User;

import static org.junit.jupiter.api.Assertions.*;

import Domain.Messenger.Messenger;
import Domain.User.UserProfile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserProfileTest {

    UserProfile userProfile;
    @BeforeEach
    void setup() {

        UserProfile userProfile = new UserProfile();
    }

    @Test
    void testSetFirstName_shouldFail_whenFirstNameIsNull() {

        try {

            userProfile.setFirstName(null);
            assertThrows(NullPointerException.class, () -> userProfile.setFirstName(null));
        } catch (NullPointerException e) {

            assertEquals("First name cannot be null", e.getMessage());
        }
    }

    @Test
    void testSetLastName_shouldFail_whenFirstNameIsBlank() {

        try {

            userProfile.setFirstName("");
            assertThrows(NullPointerException.class, () -> userProfile.setFirstName(""));
        } catch (AssertionError e) {

            assertEquals("First name cannot be blank", e.getMessage());
        }
    }

    @Test
    void testSetLastName_shouldFail_whenLastNameIsNull() {

        try {

            userProfile.setLastName(null);
            assertThrows(NullPointerException.class, () -> userProfile.setFirstName(null));
        } catch (NullPointerException e) {

            assertEquals("First name cannot be null", e.getMessage());
        }
    }

    @Test
    void testSetLastName_shouldFail_whenLastNameIsBlank() {

        try {

            userProfile.setFirstName(null);
            assertThrows(NullPointerException.class, () -> userProfile.setFirstName(null));
        } catch (NullPointerException e) {

            assertEquals("First name cannot be null", e.getMessage());
        }
    }
}