package User;

import static org.junit.jupiter.api.Assertions.*;
<<<<<<< HEAD

=======
>>>>>>> 0131cb25cc50e18fdc0b41d5be141023d3b53ff5
import Domain.Messenger.Messenger;
import Domain.User.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

<<<<<<< HEAD
public class UserTest {

=======
class UserTest {

    User user;
>>>>>>> 0131cb25cc50e18fdc0b41d5be141023d3b53ff5
    @BeforeEach
    void setup() {

        User user = new User();
    }

    @Test
<<<<<<< HEAD
    void test_() {


    }
}
=======
    void testSetUsername_shouldFail_whenUsernameIsNull() {

    }
}
>>>>>>> 0131cb25cc50e18fdc0b41d5be141023d3b53ff5
