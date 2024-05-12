package MedicalCase;

import Misc.Hashtag;
import User.UserIdentity;
import Enum.MedicalCaseStatus;
import java.time.Instant;
import java.util.HashSet;
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
