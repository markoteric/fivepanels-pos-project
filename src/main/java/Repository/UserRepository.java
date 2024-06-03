package Repository;

import Domain.User.User;

import java.time.Instant;
import java.util.*;

public class UserRepository {

    private static final HashMap<UUID, User> map = new HashMap<>();

    public static Optional<User> findById(UUID id) {
        return Optional.ofNullable(map.get(id));
    }

    public static List<User> findAll() {
        return new ArrayList<>(map.values());
    }

    public static User save(User entity) {
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

    public static Optional<User> findByEmail(String email) {
        return findAll().stream().filter(user -> user.getEmail().getEmail().equals(email)).findFirst();
    }
}
