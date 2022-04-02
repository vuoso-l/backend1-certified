package com.example.proyectoClinica.repository.daos;

import java.util.List;

public interface IDao<T> {
    public T register(T t) throws Exception;

    public void delete(Long id);

    public T findOneById(Long id);

    public T findOneByEmail(String email);

    public List<T> findAll();

    public T update(T t);
}
