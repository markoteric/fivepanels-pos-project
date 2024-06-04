package OtherTests;

import Domain.User.Misc.Hashtag;
import Foundation.Exception.AssertionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HashtagTest {

    @Test
    public void test_Hashtag_ShouldCreateValidHashtag() {

        Hashtag hashtag = new Hashtag("#Health");
        assertEquals("#Health", hashtag.getTag());
    }

    @Test
    public void test_Hashtag_ShouldThrowException_WhenTagIsNull() {

        assertThrows(AssertionException.class, () -> new Hashtag(null));
    }

    @Test
    public void test_Hashtag_ShouldThrowException_WhenTagIsBlank() {

        assertThrows(AssertionException.class, () -> new Hashtag(""));
    }

    @Test
    public void test_Hashtag_ShouldThrowException_WhenTagDoesNotStartWithHash() {

        assertThrows(AssertionException.class, () -> new Hashtag("Health"));
    }

    @Test
    public void test_Hashtag_ShouldThrowException_WhenTagIsTooShort() {

        assertThrows(AssertionException.class, () -> new Hashtag("#"));
    }

    @Test
    public void test_Hashtag_EqualsAndHashCode_ShouldBeTrueForSameTag() {

        Hashtag hashtag1 = new Hashtag("#Health");
        Hashtag hashtag2 = new Hashtag("#Health");
        assertEquals(hashtag1, hashtag2);
        assertEquals(hashtag1.hashCode(), hashtag2.hashCode());
    }

    @Test
    public void test_Hashtag_EqualsAndHashCode_ShouldBeFalseForDifferentTags() {

        Hashtag hashtag1 = new Hashtag("#Health");
        Hashtag hashtag2 = new Hashtag("#Fitness");
        assertNotEquals(hashtag1, hashtag2);
        assertNotEquals(hashtag1.hashCode(), hashtag2.hashCode());
    }

    @Test
    public void test_Hashtag_ToString_ShouldReturnCorrectString() {

        Hashtag hashtag = new Hashtag("#Health");
        assertEquals("#Health", hashtag.toString());
    }
}
