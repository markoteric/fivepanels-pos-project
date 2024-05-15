package Domain.Media;

import Domain.Misc.Assertion;

import java.net.MalformedURLException;
import java.net.URL;

public class MediaContent extends Media {

    private Media content;

    public MediaContent() throws MalformedURLException {

        super();
    }

    public MediaContent(String contentType, String image, long l, URL url) throws MalformedURLException {
        super();
        this.content = new Media();
        this.content.setContentType(contentType);
        this.content.setFileName(image);
        this.content.setFileSize(l);
        this.content.setUrl(url);

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
