package model.dao;

import java.util.List;

public interface GenereicDao<T> extends AutoCloseable{

    boolean create(T entity);
    boolean update(T entity);
    boolean delete(int id);
    T findById(int id);
    List<T> findAll();

    void close();
}
