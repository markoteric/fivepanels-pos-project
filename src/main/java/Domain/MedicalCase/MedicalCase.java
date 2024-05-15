package Domain.MedicalCase;

import Domain.Media.MediaContent;
import Domain.Media.TextContent;
import Domain.Misc.Assertion;
import Domain.Misc.Hashtag;
import Domain.User.User;
import Domain.User.UserIdentity;
import Domain.Enum.MedicalCaseStatus;
import Domain.User.UserProfile;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class MedicalCase {

    private Integer id;
    private Set<Hashtag> hashtags;
    private UserIdentity owner;
    private TextContent headline;
    private TextContent content;
    private MediaContent attachment;
    private Integer viewCount = 0;
    private Instant createdAt;
    private Instant updatedAt;
    private MedicalCaseStatus status;
    private Integer likeCount = 0;

    public MedicalCase() {

        this.id = UUID.randomUUID().hashCode();
        this.hashtags.add(new Hashtag("hashtagTest1"));
        this.hashtags.add(new Hashtag("hashtagTest2"));
        this.owner = UserIdentity.getUser();
        this.viewCount = 0;
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
        this.status = MedicalCaseStatus.UNSOLVED;
        this.likeCount = 0;
    }

    public MedicalCase(Set<Hashtag> hashtags, TextContent headline, TextContent content, MediaContent attachment) {

        this.hashtags = hashtags;
        this.headline = headline;
        this.content = content;
        this.attachment = attachment;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<Hashtag> getHashtags() {
        return hashtags;
    }

    public void setHashtags(Set<Hashtag> hashtags) {
        this.hashtags = hashtags;
    }

    public UserIdentity getOwner() {
        return owner;
    }

    public void setOwner(UserIdentity owner) {
        this.owner = owner;
    }

    public TextContent getHeadline() {
        return headline;
    }

    public void setHeadline(TextContent headline) {
        this.headline = headline;
    }

    public TextContent getContent() {
        return content;
    }

    public void setContent(TextContent content) {
        this.content = content;
    }

    public MediaContent getAttachment() {
        return attachment;
    }

    public void setAttachment(MediaContent attachment) {
        this.attachment = attachment;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
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

    public void addHashtag(String hashtag) {

        this.hashtags.add(new Hashtag(hashtag));
    }

    public void addAttachment(List<MediaContent> attachments) {

        this.attachment = (MediaContent) attachments;
    }

    public void removeAttachment(MediaContent attachment) {

        Assertion.isNotNull(attachment, "attachment");
        Assertion.isNotNull(attachment.getContent(), "attachment");
        this.attachment = (MediaContent) attachment;
    }

    public void incrementViewCount() {

        this.viewCount++;
    }
}
