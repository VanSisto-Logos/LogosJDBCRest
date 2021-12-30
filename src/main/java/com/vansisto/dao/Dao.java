package com.vansisto.dao;

import java.util.List;

public interface Dao<T> {
    T create(T t);
    T getById(int id);
    List<T> getAll();
    void deleteById(int id);
    void update(T t);
    boolean isExist(int id);
}
