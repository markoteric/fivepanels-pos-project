package OtherTests;

import Domain.User.Misc.Language;
import Foundation.Exception.AssertionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LanguageTest {

    @Test
    public void test_IsValidLanguage_ShouldReturnTrue_ForValidLanguage() {

        Language language = new Language("English");
        assertEquals("English", language.getLanguage());
    }

    @Test
    public void test_IsValidLanguage_ShouldReturnFalse_WhenLanguageIsInvalid() {

        assertThrows(AssertionException.class, () -> new Language("Foobar"));
    }

    @Test
    public void test_IsValidLanguage_ShouldBeCaseInsensitive() {

        Language language = new Language("EnGlIsH");
        assertEquals("EnGlIsH", language.getLanguage());
    }

    @Test
    public void test_IsValidLanguage_ShouldTrimWhitespace() {

        Language language = new Language("  English  ");
        assertEquals("  English  ", language.getLanguage());
    }

    @Test
    public void test_SetLanguage_ShouldThrowException_WhenLanguageIsInvalid() {

        Language language = new Language("English");
        assertThrows(AssertionException.class, () -> language.setLanguage("Foobar"));
    }

    @Test
    public void test_SetLanguage_ShouldNotThrowException_WhenLanguageIsValid() {

        Language language = new Language("English");
        assertDoesNotThrow(() -> language.setLanguage("Spanish"));
    }
}
