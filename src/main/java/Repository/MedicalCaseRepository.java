package Repository;

import Domain.MedicalCase.MedicalCase;
import Domain.User.User;

import java.time.Instant;
import java.util.*;

public class MedicalCaseRepository {

    private static final HashMap<UUID, MedicalCase> map = new HashMap<>();

    public static Optional<MedicalCase> findById(UUID id) {
        return Optional.ofNullable(map.get(id));
    }
    
    public static List<MedicalCase> findAll() {
        return new ArrayList<>(map.values());
    }
    
    public static MedicalCase save(MedicalCase entity) {
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
