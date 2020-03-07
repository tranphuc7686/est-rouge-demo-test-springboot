package com.example.estrougedemospringboot.repositories;

import org.springframework.data.jpa.provider.HibernateUtils;

import javax.persistence.criteria.CriteriaBuilder;

public abstract class GenericRepository<T> {


    protected final String idFieldName = "id";


    protected final Class<T> persistentClass;

    protected GenericRepository(Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }

}
