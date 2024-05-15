package Domain.MedicalCase;

import Domain.Misc.Hashtag;
import Domain.User.User;
import Domain.User.UserIdentity;
import Domain.Enum.MedicalCaseStatus;
import Domain.User.UserProfile;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

public class MedicalCase {

    private Integer id;
    private Set<Hashtag> hashtags;
    private UserIdentity owner;
    private Integer viewCount;
    private Instant createdAt;
    private Instant updatedAt;
    private MedicalCaseStatus status;
    private Integer likeCount;

    public MedicalCase() {

        this.id = new UUID(16, 16).hashCode();
        this.owner = UserIdentity.getUser();
        this.viewCount = 0;
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
        this.status = MedicalCaseStatus.DRAFT;
        this.likeCount = 0;
    }
}
