package com.poly.dao;

import java.util.List;

import com.poly.model.Favorite;
import com.poly.model.Share;
import com.poly.util.JpaUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class ReportDAO {

    // ===================== 1. REPORT: THỐNG KÊ YÊU THÍCH =====================
    public List<Object[]> getFavoriteStats() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            String jpql =
                "SELECT f.video.title, COUNT(f.user.userId) " +
                "FROM Favorite f " +
                "GROUP BY f.video.title " +
                "ORDER BY COUNT(f.user.userId) DESC";

            return em.createQuery(jpql, Object[].class).getResultList();
        } finally {
            em.close();
        }
    }

    // ===================== 2. REPORT: DS NGƯỜI YÊU THÍCH THEO VIDEO =====================
    public List<Favorite> getUsersByVideo(int videoId) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            String jpql =
                "SELECT f FROM Favorite f " +
                "JOIN FETCH f.user " +
                "JOIN FETCH f.video " +
                "WHERE f.video.id = :vid";

            return em.createQuery(jpql, Favorite.class)
                     .setParameter("vid", videoId)
                     .getResultList();
        } finally {
            em.close();
        }
    }

    // ===================== 3. REPORT: NGƯỜI GỬI & NHẬN SHARE =====================
    public List<Share> getSharesByVideo(int videoId) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            String jpql =
                "SELECT s FROM Share s " +
                "JOIN FETCH s.user " +
                "JOIN FETCH s.video " +
                "WHERE s.video.id = :vid " +
                "ORDER BY s.shareDate DESC";

            return em.createQuery(jpql, Share.class)
                     .setParameter("vid", videoId)
                     .getResultList();
        } finally {
            em.close();
        }
    }
}
