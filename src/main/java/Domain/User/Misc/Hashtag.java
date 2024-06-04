package Domain.User.Misc;

import Foundation.Assertion.Assertion;
import Foundation.Exception.AssertionException;

import java.util.Objects;

public class Hashtag {


    // Not null, start with #, not blank
    private String hashtag;

    public Hashtag(String hashtag) {

        setTag(hashtag);
    }

    public String getTag() {

        return hashtag;
    }

    public void setTag(String hashtag) {

        Assertion.isNotNull(hashtag, "tag");
        Assertion.isNotBlank(hashtag, "tag");
        Assertion.hasMinLength(hashtag, 2, "tag");
        if (!hashtag.startsWith("#")) {

            throw new AssertionException("tag must start with #");
        }

        this.hashtag = hashtag;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hashtag hashtag1 = (Hashtag) o;
        return Objects.equals(hashtag, hashtag1.hashtag);
    }

    @Override
    public int hashCode() {

        return Objects.hash(hashtag);
    }

    @Override
    public String toString() {

        return hashtag;
    }
}
