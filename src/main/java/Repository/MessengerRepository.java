package Repository;

import Domain.Messenger.Messenger;

import java.time.Instant;
import java.util.*;

public class MessengerRepository {

    private static final HashMap<UUID, Messenger> map = new HashMap<>();

    public static Optional<Messenger> findById(UUID id) {
        return Optional.ofNullable(map.get(id));
    }

    public static List<Messenger> findAll() {
        return new ArrayList<>(map.values());
    }

    public static Messenger save(Messenger entity) {
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
