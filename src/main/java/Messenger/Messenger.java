package Messenger;

import User.UserIdentity;

import java.util.List;
import java.util.UUID;
import java.util.Set;

public class Messenger {
    private UUID id;
    private Set<UserIdentity> members;
    private List<Message> messageHistory;

}
