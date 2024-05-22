package Domain.Messenger;
import java.util.List;



public class Chat extends Messenger {

    private String name;
    private List<UserIdentity> members;
    private Messenger messenger;

    public Chat(String name, List<UserIdentity> members, Messenger messenger) {

        this.name = name;
        this.members = members;
        this.messenger = messenger;
    }

    public Chat() {

    }

    public void setName(String groupName) {
    }

    public Object getName() {
        return name;
    }
}
