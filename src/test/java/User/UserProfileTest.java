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

        userProfile.setFirstName(null);
        assertThrows(NullPointerException.class, () -> userProfile.setFirstName(null));
    }

    @Test
    void testSetLastName_shouldFail_whenFirstNameIsBlank() {

        userProfile.setFirstName("");
        assertThrows(NullPointerException.class, () -> userProfile.setFirstName(""));
    }

    @Test
    void testSetLastName_shouldFail_whenLastNameIsNull() {

        userProfile.setLastName(null);
        assertThrows(NullPointerException.class, () -> userProfile.setFirstName(null));
    }

    @Test
    void testSetLastName_shouldFail_whenLastNameIsBlank() {

        userProfile.setFirstName(null);
        assertThrows(NullPointerException.class, () -> userProfile.setFirstName(null));
    }
}