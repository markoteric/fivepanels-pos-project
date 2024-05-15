package Domain.Media;

import Domain.Misc.Assertion;

public class TextContent extends Media {
    private String content;

    public TextContent() {

        this.content = "Paric Teric Test";
    }

    public TextContent(String content) {

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
