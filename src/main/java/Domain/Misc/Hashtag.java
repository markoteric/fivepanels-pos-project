package Domain.Misc;

import java.util.Set;

public class Hashtag {

    private String hashtag;

    public Hashtag(String hashtag) {

        setHashtag(hashtag);
    }

    public String getHashtag() {

        return hashtag;
    }

    public void setHashtag(String hashtag) {

        Assertion.isNotNull(hashtag, "hashtag");
        Assertion.isNotBlank(hashtag, "hashtag");
        this.hashtag = hashtag;
    }
}
