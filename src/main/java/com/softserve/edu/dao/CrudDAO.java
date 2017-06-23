package com.softserve.edu.dao;

public interface CrudDAO<E> extends ReadDAO<E> {

    void add(E element);

    void update(E element);

    void delete(E element);

    void deleteById(Long id);

}
