package Repository;

import Domain.Messenger.Message;

import java.time.Instant;
import java.util.*;

public class MessageRepository {

    private static final HashMap<UUID, Message> map = new HashMap<>();

    public static Optional<Message> findById(UUID id) {

        return Optional.ofNullable(map.get(id));
    }

    public static List<Message> findAll() {

        return new ArrayList<>(map.values());
    }

    public static Message save(Message entity) {

        map.put(entity.getId(), entity);
        entity.setUpdatedAt(Instant.now());
        return entity;
    }

    public static int count() {

        return map.size();
    }
    public static void deleteById(UUID id) {
        map.remove(id);
    }

    public static boolean existsById(UUID id) {
        return map.get(id) != null;
    }
}
