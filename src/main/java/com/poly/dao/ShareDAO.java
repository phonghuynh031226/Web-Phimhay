package com.poly.dao;

import java.util.List;

import com.poly.model.Share;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import com.poly.util.JpaUtil;

public class ShareDAO extends AbstractDAO<Share> {

    public ShareDAO() {
        super(Share.class);
    }

    // Lấy danh sách SHARE kèm USER + VIDEO đầy đủ thông tin
    public List<Share> findAllFull() {
        EntityManager em = JpaUtil.getEntityManager();

        String jpql = "SELECT s FROM Share s "
                     + "JOIN FETCH s.user "
                     + "JOIN FETCH s.video "
                     + "ORDER BY s.shareDate DESC";

        TypedQuery<Share> query = em.createQuery(jpql, Share.class);

        List<Share> result = query.getResultList();
        em.close();
        return result;
    }
}
