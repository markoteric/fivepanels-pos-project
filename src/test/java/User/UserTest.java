package User;

import static org.junit.jupiter.api.Assertions.*;
import Domain.Messenger.Messenger;
import Domain.User.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {

    User user;
    @BeforeEach
    void setup() {

        User user = new User();
    }

    @Test
    void testSetUsername_shouldFail_whenUsernameIsNull() {

    }
}