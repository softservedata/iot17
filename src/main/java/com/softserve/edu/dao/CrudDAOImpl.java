package com.softserve.edu.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;

import org.hibernate.Session;

abstract class CrudDAOImpl<E> implements CrudDAO<E> {
    private Class<E> entityClass;
    private Session session;

    public CrudDAOImpl(Class<E> entityClass, Session session) {
        this.entityClass = entityClass;
        this.session = session;
    }

    // @SuppressWarnings("unchecked")
    @Override
    public E getById(Long id) {
        // E element = (E) session.load(entityClass, id); // get proxy
        // E element = (E) session.get(entityClass, id);
        return (E) session.get(entityClass, id);
    }

    @SuppressWarnings({ "unchecked", "deprecation" })
    @Override
    public List<E> getAll() {
        List<E> elements = new ArrayList<E>();
        // TODO deprecation
        elements = session.createCriteria(entityClass).list();
        // elements =
        // session.getCriteriaBuilder().createQuery(entityClass).getOrderList();
        // Create CriteriaBuilder
        //CriteriaBuilder builder = session.getCriteriaBuilder();
        // Create CriteriaQuery
        //CriteriaQuery<E> criteria = builder.createQuery(entityClass).getOrderList();
        return elements;
    }

    @Override
    public void add(E element) {
        session.save(element);
    }

    @Override
    public void update(E element) {
        session.update(element);
    }

    @Override
    public void delete(E element) {
        session.delete(element);
    }

    @Override
    public void deleteById(Long id) {
        session.delete(getById(id));
    }

}
