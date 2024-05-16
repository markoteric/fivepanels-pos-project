package Domain.Messenger;
import Domain.User.UserIdentity;
import java.util.List;



public class Group extends Messenger {

    private String name;
    private List<UserIdentity> members;
    private Messenger messenger;

    public Group(String name, List<UserIdentity> members, Messenger messenger) {

        this.name = name;
        this.members = members;
        this.messenger = messenger;
    }

    public Group() {

    }

    public void setName(String groupName) {
    }

    public Object getName() {
        return name;
    }
}
