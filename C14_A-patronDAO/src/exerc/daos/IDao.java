package exerc.daos;

import java.util.List;

public interface IDao<T> {
    public T register(T t) throws Exception;

    public void delete(Long id);

    public T findOne(Long id);

    public List<T> findAll();
}
