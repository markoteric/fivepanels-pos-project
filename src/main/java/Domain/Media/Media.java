package Domain.Media;

import Domain.Misc.Assertion;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Instant;

public class Media {

    private String mimeType;
    private String fileName;
    private Long fileSize;
    private Instant createdAt;
    private URL url;

    public Media() throws MalformedURLException {

        this.mimeType = "image/jpeg";
        this.fileName = "test.jpg";
        this.fileSize = 1024L;
        this.createdAt = Instant.now();
        this.url = new URL("https://www.google.com");
    }

    public String getMimeType() {

        return mimeType;
    }

    public String getFileName() {

        return fileName;
    }

    public void setFileName(String fileName) {

        Assertion.isNotNull(fileName, "fileName");
        Assertion.isNotBlank(fileName, "fileName");
        this.fileName = fileName;
    }

    public Long getFileSize() {

        return fileSize;
    }

    public Instant getCreatedAt() {

        return createdAt;
    }

    public void setCreatedAt() {

        this.createdAt = Instant.now();
    }

    public URL getUrl() {

        return url;
    }

    public void setUrl(URL url) {

        Assertion.isNotNull(url, "url");
        Assertion.isNotBlank(url.toString(), "url");
        this.url = url;
    }

    public void setContentType(String contentType) {

    }

    public void setFileSize(long l) {

    }
}
