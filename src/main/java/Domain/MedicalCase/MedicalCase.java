package Domain.MedicalCase;

import Domain.Misc.Hashtag;
import Domain.User.UserIdentity;
import Domain.Enum.MedicalCaseStatus;
import java.time.Instant;
import java.util.Set;

public class MedicalCase {

    private Integer id;
    private Set<Hashtag> hashtags;
    private UserIdentity owner;
    private Integer viewCount;
    private Instant createdAt;
    private Instant updatedAt;
    private MedicalCaseStatus status;
    private Integer likeCount;






}
