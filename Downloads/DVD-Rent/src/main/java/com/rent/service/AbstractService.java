package com.rent.service;

import com.rent.exception.EntityNotFoundEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public class AbstractService<T> {

    protected final JpaRepository<T, Long> repository;

    public AbstractService(JpaRepository<T, Long> repository) {
        this.repository = repository;
    }

    public T findById(Long id) {
        return repository.findById(id).orElseThrow(EntityNotFoundEx::new);
    }

    public List<T> findAll() {
        return repository.findAll();
    }

    public T deleteById(Long id) {
        T entity = findById(id);
        repository.deleteById(id);
        return entity;
    }

    public T save(T entity) {
        return repository.save(entity);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public Double roundUp(Double number){
        return Math.round(number * 1000.0) / 1000.0;
    }
}
