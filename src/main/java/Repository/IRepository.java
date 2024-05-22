package Repository;

import java.util.List;
import java.util.UUID;

public interface IRepository<T> {

    public void save(T baseEntity);

    public void delete(T baseEntity);

    public T findById(UUID id);

    public List<T> findAll();
}
