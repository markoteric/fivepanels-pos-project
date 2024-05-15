package Domain.Media;

import Domain.Misc.Assertion;

public class MediaContent extends Media {

    private Media content;

    public MediaContent() {

        super();
    }

    public Media getContent() {

        return content;
    }

    public void setContent(Media content) {

        Assertion.isNotNull(content, "content");
        Assertion.isNotBlank(content.toString(), "content");
        this.content = content;
    }
}
