package Domain.Messenger;

import Domain.Misc.Assertion;
import Domain.User.UserIdentity;

import java.util.List;
import java.util.UUID;
import java.util.Set;

public class Messenger {
    private UUID id;
    private Set<UserIdentity> members;
    private List<Message> messageHistory;

    public UUID getId() {

        return id;
    }

    public void setId(UUID id) {

        this.id = id;
    }

    public Set<UserIdentity> getMembers() {

        return members;
    }

    public void setMembers(Set<UserIdentity> members) {

        Assertion.isNotNull(members, "members");
        Assertion.isNotBlank(members.toString(), "members");
        this.members = members;
    }

    public List<Message> getMessageHistory() {

        return messageHistory;
    }
}
