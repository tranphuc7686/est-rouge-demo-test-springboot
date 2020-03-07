package com.example.estrougedemospringboot.repositories;

import java.util.List;


public interface BaseRepository<T> {
        List<T> getAll();

        T getById(final int id);

        void add(T entity);

        void update(T entity, Integer oldId);

        boolean remove(T entity);
}
