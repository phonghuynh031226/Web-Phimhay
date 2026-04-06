package com.poly.dao;

import java.util.List;

import com.poly.model.User;
import com.poly.util.JpaUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class UserDAO extends AbstractDAO<User> {

    public UserDAO() {
        super(User.class);
    }

    // ======= FIND BY USERNAME =======
    public User findByUsername(String username) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            String jpql = "SELECT u FROM User u WHERE u.username = :uname";
            TypedQuery<User> query = em.createQuery(jpql, User.class);
            query.setParameter("uname", username);

            List<User> list = query.getResultList();
            return list.isEmpty() ? null : list.get(0);

        } finally {
            em.close();
        }
    }

    // ======= FIND BY EMAIL =======
    public User findByEmail(String email) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            String jpql = "SELECT u FROM User u WHERE u.email = :email";
            TypedQuery<User> query = em.createQuery(jpql, User.class);
            query.setParameter("email", email);

            List<User> list = query.getResultList();
            return list.isEmpty() ? null : list.get(0);

        } finally {
            em.close();
        }
    }

    // NOTE:
    // Không override update() hoặc create() hoặc delete()
    // vì AbstractDAO đã làm CHUẨN & SẠCH rồi.
}
