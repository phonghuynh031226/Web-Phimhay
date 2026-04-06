package com.poly.dao;

import java.util.List;

import com.poly.model.Video;
import com.poly.util.JpaUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.EntityTransaction;

public class VideoDAO extends AbstractDAO<Video> {

    public VideoDAO() {
        super(Video.class);
    }

    // ================= FIND BY ID (STRING) =================
    public Video findById(String id) {
        try {
            Integer videoId = Integer.valueOf(id);
            return super.findById(videoId);
        } catch (Exception e) {
            return null;
        }
    }

    // ================= LOAD ACTIVE VIDEOS =================
    public List<Video> findActive() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            String jpql = "SELECT v FROM Video v WHERE v.active = true ORDER BY v.views DESC";
            return em.createQuery(jpql, Video.class).getResultList();
        } finally {
            em.close();
        }
    }

    // ================= SEARCH BY KEYWORD =================
    public List<Video> findByKeyword(String keyword) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            String jpql = "SELECT v FROM Video v WHERE v.title LIKE :kw ORDER BY v.views DESC";
            return em.createQuery(jpql, Video.class)
                     .setParameter("kw", "%" + keyword + "%")
                     .getResultList();
        } finally {
            em.close();
        }
    }

    // ================= PAGING (HOME PAGE) =================
    public List<Video> findPage(int page, int size) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            String jpql = "SELECT v FROM Video v ORDER BY v.views DESC";
            TypedQuery<Video> query = em.createQuery(jpql, Video.class);
            query.setFirstResult((page - 1) * size);
            query.setMaxResults(size);

            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // ================= COUNT ALL VIDEO =================
    public int count() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Long count = em.createQuery("SELECT COUNT(v) FROM Video v", Long.class)
                           .getSingleResult();
            return count.intValue();
        } finally {
            em.close();
        }
    }

    // ================= SUGGEST VIDEO =================
    public List<Video> findSuggest(String videoId) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Integer id = Integer.valueOf(videoId);

            String jpql = "SELECT v FROM Video v WHERE v.id <> :id ORDER BY v.views DESC";
            return em.createQuery(jpql, Video.class)
                     .setParameter("id", id)
                     .setMaxResults(8)
                     .getResultList();
        } finally {
            em.close();
        }
    }

 // ================= DELETE VIDEO (CHO PHÉP XÓA DÙ CÓ YÊU THÍCH) =================
    public void delete(Integer id) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();

        try {
            tran.begin();

            // 1) Xóa bảng Favorites trước (tránh lỗi FK)
            em.createQuery("DELETE FROM Favorite f WHERE f.video.id = :vid")
              .setParameter("vid", id)
              .executeUpdate();

            // 2) Xóa bảng Shares nếu có
            em.createQuery("DELETE FROM Share s WHERE s.video.id = :vid")
              .setParameter("vid", id)
              .executeUpdate();

            // 3) Cuối cùng xóa Video
            Video v = em.find(Video.class, id);
            if (v != null) {
                em.remove(em.contains(v) ? v : em.merge(v));
            }

            tran.commit();
        } catch (Exception e) {
            tran.rollback();
            e.printStackTrace();
            throw e;
        } finally {
            em.close();
        }
    }


}
