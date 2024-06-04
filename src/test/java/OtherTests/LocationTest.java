package OtherTests;

import Domain.User.Misc.Location;
import Foundation.Exception.AssertionException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LocationTest {

    @Test
    public void test_IsValidCity_ShouldReturnTrue_ForValidCity() {

        Location location = new Location("London");
        assertEquals("London", location.getCity());
    }

    @Test
    public void test_IsValidCity_ShouldThrowException_ForInvalidCity() {

        assertThrows(AssertionException.class, () -> new Location("Foobar"));
    }

    @Test
    public void test_IsValidCity_ShouldBeCaseInsensitive() {

        Location location = new Location("LoNDoN");
        assertEquals("LoNDoN", location.getCity());
    }

    @Test
    public void test_IsValidCity_ShouldTrimWhitespace() {

        Location location = new Location("  London  ");
        assertEquals("  London  ", location.getCity());
    }

    @Test
    public void test_SetCity_ShouldThrowException_WhenCityIsInvalid() {

        Location location = new Location("London");
        assertThrows(AssertionException.class, () -> location.setCity("Foobar"));
    }

    @Test
    public void test_SetCity_ShouldNotThrowException_WhenCityIsValid() {

        Location location = new Location("London");
        assertDoesNotThrow(() -> location.setCity("Washington, D.C."));
    }
}
