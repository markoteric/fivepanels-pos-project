package User;

import static org.junit.jupiter.api.Assertions.*;

import Domain.Messenger.Messenger;
import Domain.User.UserProfile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

<<<<<<< HEAD
public class UserProfileTest {
=======
class UserProfileTest {

    UserProfile userProfile;
>>>>>>> 0131cb25cc50e18fdc0b41d5be141023d3b53ff5

    @BeforeEach
    void setup() {

        UserProfile userProfile = new UserProfile();
    }

    @Test
<<<<<<< HEAD
    void test() {


    }
}
=======
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
>>>>>>> 0131cb25cc50e18fdc0b41d5be141023d3b53ff5
