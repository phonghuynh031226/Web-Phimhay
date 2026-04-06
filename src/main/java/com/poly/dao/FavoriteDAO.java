package com.poly.dao;

import java.util.List;

import com.poly.model.Favorite;
import com.poly.util.JpaUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class FavoriteDAO extends AbstractDAO<Favorite> {

    public FavoriteDAO() {
        super(Favorite.class);
    }

    // ======= LẤY TẤT CẢ FAVORITE CỦA USER =======

    
    public List<Favorite> findByUser(Integer userId) {
        EntityManager em = JpaUtil.getEntityManager();

        String jpql = "SELECT f FROM Favorite f "
                     + "JOIN FETCH f.video "
                     + "WHERE f.user.userId = :uid";

        TypedQuery<Favorite> q = em.createQuery(jpql, Favorite.class);
        q.setParameter("uid", userId);

        return q.getResultList();
    }


    // ======= KIỂM TRA FAVORITE TỒN TẠI =======
    public Favorite findOne(Integer userId, Integer videoId) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            String jpql = "SELECT f FROM Favorite f WHERE f.user.userId = :uid AND f.video.id = :vid";
            TypedQuery<Favorite> query = em.createQuery(jpql, Favorite.class);
            query.setParameter("uid", userId);
            query.setParameter("vid", videoId);

            List<Favorite> list = query.getResultList();
            return list.isEmpty() ? null : list.get(0);

        } finally {
            em.close();
        }
    }

    // ======= XOÁ FAVORITE =======
    public void deleteFavorite(Integer userId, Integer videoId) {
        Favorite f = findOne(userId, videoId);
        if (f == null) return;

        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();

        try {
            tran.begin();
            Favorite merged = em.merge(f);   // luôn merge trước khi remove
            em.remove(merged);
            tran.commit();
        } catch (Exception e) {
            tran.rollback();
            throw e;
        } finally {
            em.close();
        }
    }
    
 // XÓA TẤT CẢ FAVORITE THEO VIDEO
    public void deleteByVideo(Integer videoId) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();

        try {
            tran.begin();

            em.createQuery("DELETE FROM Favorite f WHERE f.video.id = :vid")
              .setParameter("vid", videoId)
              .executeUpdate();

            tran.commit();
        } catch (Exception e) {
            tran.rollback();
            throw e;
        } finally {
            em.close();
        }
    }


    
    
}
