package parcial.daos;

import java.util.List;

public interface IDao<T> {
    public T registrar(T t) throws Exception;

    public void eliminar(Long id);

    public T listarUno(Long id);

    public List<T> listarTodos();
}
