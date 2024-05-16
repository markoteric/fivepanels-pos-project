package Domain.Media;

import Domain.Assertion.Assertion;

import java.net.MalformedURLException;

public class TextContent extends Media {
    
    private String content;

    public TextContent() throws MalformedURLException {
        super();

        this.content = "Paric Teric Test";
    }

    public TextContent(String content) throws MalformedURLException {
        super();

        setContent(content);
    }

    public String getContent() {

        return content;
    }

    public void setContent(String content) {

        Assertion.isNotNull(content, "content");
        Assertion.isNotBlank(content, "content");
        this.content = content;
    }
}
