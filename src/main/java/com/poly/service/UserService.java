package com.poly.service;

import com.poly.dao.UserDAO;
import com.poly.model.User;
import java.util.List;

public class UserService {

    private UserDAO dao = new UserDAO();

    // =============== LOGIN ===============
    public User login(String username, String password) {
        if (username == null || password == null) return null;

        User user = dao.findByUsername(username);

        if (user == null) return null;
        if (!user.getActivated()) return null;
        if (!user.getPassword().equals(password)) return null;

        return user;
    }

    // =============== GET ALL USERS ===============
    public List<User> findAll() {
        return dao.findAll();
    }

    // =============== FIND BY ID ===============
    public User findById(Integer id) {
        return dao.findById(id);
    }

    // =============== FIND BY USERNAME ===============
    public User findByUsername(String username) {
        return dao.findByUsername(username);
    }

    // =============== FIND BY EMAIL ===============
    public User findByEmail(String email) {
        return dao.findByEmail(email);
    }

    // =============== CREATE USER ===============
    public User create(User user) {
        return dao.create(user);
    }

    // =============== UPDATE USER ===============
    public User update(User user) {
        return dao.update(user);
    }

    // =============== DELETE USER ===============
    public void delete(Integer id) {
        dao.delete(id);
    }

    // =============== UPDATE PASSWORD ===============
    public void updatePassword(Integer userId, String newPassword) {
        User user = dao.findById(userId);
        if (user != null) {
            user.setPassword(newPassword);
            dao.update(user);
        }
    }

    // =============== UPDATE PROFILE ===============
    public void updateProfile(Integer userId, String fullname) {
        User user = dao.findById(userId);
        if (user != null) {
            user.setFullname(fullname);
            dao.update(user);
        }
    }
    
 // =============== SEND PASSWORD TO EMAIL ===============
    public void sendPasswordEmail(User user) {
        // TẠM THỜI GIẢ LẬP GỬI EMAIL
        System.out.println("📧 Gửi email đến: " + user.getEmail());
        System.out.println("🔐 Mật khẩu của bạn là: " + user.getPassword());
    }

}
