package com.poly.dao;

import java.util.List;

import com.poly.util.JpaUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public abstract class AbstractDAO<T> {

    private final Class<T> entityClass;

    protected AbstractDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    // ======= FIND ALL =======
    public List<T> findAll() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            String jpql = "SELECT e FROM " + entityClass.getSimpleName() + " e";
            TypedQuery<T> query = em.createQuery(jpql, entityClass);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // ======= FIND BY ID =======
    public T findById(Object id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.find(entityClass, id);
        } finally {
            em.close();
        }
    }

    // ======= CREATE =======
    public T create(T entity) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();
        try {
            tran.begin();
            em.persist(entity);
            tran.commit();
            return entity;
        } catch (Exception e) {
            tran.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    // ======= UPDATE =======
    public T update(T entity) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();
        try {
            tran.begin();
            entity = em.merge(entity);
            tran.commit();
            return entity;
        } catch (Exception e) {
            tran.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    // ======= DELETE =======
    public void delete(Object id) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();
        try {
            T entity = em.find(entityClass, id);
            if (entity != null) {
                tran.begin();
                em.remove(entity);
                tran.commit();
            }
        } catch (Exception e) {
            tran.rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}
