package com.example.celebsrating.dao;

import java.util.List;

public interface IRepository<T> {
    boolean create(T o);
    boolean update(T o);
    boolean delete(T o);
    T findById(int id);
    List<T> findAll();
}