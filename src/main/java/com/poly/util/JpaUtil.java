package com.poly.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public final class JpaUtil {

    private static EntityManagerFactory factory;

    static {
        try {
            factory = Persistence.createEntityManagerFactory("ASM_JAVA4");
        } catch (Exception e) {
            System.err.println("❌ LỖI KHỞI TẠO ENTITY MANAGER FACTORY!");
            e.printStackTrace();
            throw new ExceptionInInitializerError(e);
        }
    }

    private JpaUtil() {}

    // Lấy EntityManager mới
    public static EntityManager getEntityManager() {
        return factory.createEntityManager();
    }

    // Đóng EntityManagerFactory khi ứng dụng shutdown
    public static void shutdown() {
        if (factory != null && factory.isOpen()) {
            factory.close();
        }
    }
}
