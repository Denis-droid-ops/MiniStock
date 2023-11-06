package com.kuznetsov.miniStock.dao;

import java.util.List;
import java.util.Optional;


public interface Dao<T,K>{
    T save(T object);
    Optional<T> findById(K id);
    void update(T object);
    boolean delete(K id);
    List<T> findAll();

}
