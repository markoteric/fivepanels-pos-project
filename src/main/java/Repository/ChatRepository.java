package Repository;

import Domain.Messenger.Chat;

import java.time.Instant;
import java.util.*;

public class ChatRepository {

    private static final HashMap<UUID, Chat> map = new HashMap<>();

    public static Optional<Chat> findById(UUID id) {

        return Optional.ofNullable(map.get(id));
    }

    public static List<Chat> findAll() {

        return new ArrayList<>(map.values());
    }

    public static Chat save(Chat entity) {

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
