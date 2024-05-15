package Domain.MedicalCase;

import Domain.BaseEntity;
import Domain.Media.Media;
import Domain.Media.MediaContent;
import Domain.Media.TextContent;
import Domain.Misc.Assertion;
import Domain.Misc.Hashtag;
import Domain.User.User;
import Domain.User.UserIdentity;
import Domain.Enum.MedicalCaseStatus;
import Domain.User.UserProfile;

import java.net.MalformedURLException;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static Domain.Misc.Assertion.isNotBlank;
import static Domain.Misc.Assertion.isNotNull;

public class MedicalCase extends BaseEntity {

    private Integer id;
    private String headline;
    private String content;
    private Media attachment;
    private Integer viewCount = 0;
    private Instant createdAt;
    private Instant updatedAt;
    private MedicalCaseStatus status;
    private Integer likeCount = 0;
    private UserIdentity owner;
    private Set<Hashtag> hashtags;

    public MedicalCase() throws MalformedURLException {

        this.id = 0;
        this.headline = "foobar";
        this.content = "foobar, lol";
        this.attachment = new Media();
        this.viewCount = 0;
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
        this.status = MedicalCaseStatus.UNSOLVED;
        this.likeCount = 0;
        this.owner = UserIdentity.getUser();
        this.hashtags = new HashSet<Hashtag>();
    }

    public Integer getId() {

        return id;
    }

    public void setId() {

        this.id = UUID.randomUUID().hashCode();
    }

    public String getHeadline() {

        return headline;
    }

    public void setHeadline(String headline) {

        isNotNull(headline, "headline");
        isNotBlank(headline, "headline");
        this.headline = headline;
    }

    public String getContent() {

        return content;
    }

    public void setContent(String content) {
        isNotNull(content, "content");
        isNotBlank(content, "content");
        this.content = content;
    }

    public Media getAttachment() {

        return attachment;
    }

    public void setAttachment(Media attachment) {

        isNotNull(attachment, "attachment");
        this.attachment = attachment;
    }

    public Integer getViewCount() {

        return viewCount;
    }

    public void updateViewCount() {

        this.viewCount++;
    }

    public Instant getCreatedAt() {

        return createdAt;
    }

    public void setCreatedAt() {

        this.createdAt = Instant.now();
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public MedicalCaseStatus getStatus() {
        return status;
    }

    public void setStatus(MedicalCaseStatus status) {
        this.status = status;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public UserIdentity getOwner() {
        return owner;
    }

    public void setOwner(UserIdentity owner) {
        this.owner = owner;
    }

    public Set<Hashtag> getHashtags() {
        return hashtags;
    }

    public void setHashtags(Set<Hashtag> hashtags) {
        this.hashtags = hashtags;
    }
}
