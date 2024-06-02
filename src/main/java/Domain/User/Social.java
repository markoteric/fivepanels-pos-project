package Domain.User;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Social {

    private Map<UUID, UserRelationship> relationships;

    public Social() {

        this.relationships = new HashMap<>();
    }

    public Map<UUID, UserRelationship> getRelationships() {

        return relationships;
    }

    public void setRelationships(Map<UUID, UserRelationship> relationships) {

        this.relationships = relationships;
    }
}
