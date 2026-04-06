package com.poly.controller;

import java.io.IOException;

import com.poly.model.User;
import com.poly.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/register")
public class RegisterController extends HttpServlet {

    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Nếu đã login rồi → không được đăng ký
        if (req.getSession().getAttribute("user") != null) {
            resp.sendRedirect("index");
            return;
        }

        req.getRequestDispatcher("/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String fullname = req.getParameter("fullname");
        String email = req.getParameter("email");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String confirm = req.getParameter("confirm");

        // ================= CHECK RỖNG =================
        if (fullname == null || fullname.isBlank() ||
            email == null || email.isBlank() ||
            username == null || username.isBlank() ||
            password == null || password.isBlank()) {

            req.setAttribute("error", "Vui lòng nhập đầy đủ thông tin!");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
            return;
        }

        // ================= CHECK PASSWORD MATCH =================
        if (!password.equals(confirm)) {
            req.setAttribute("error", "Mật khẩu xác nhận không trùng khớp!");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
            return;
        }

        // ================= CHECK USERNAME TỒN TẠI =================
        if (userService.findByUsername(username) != null) {
            req.setAttribute("error", "Tên người dùng đã tồn tại!");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
            return;
        }

        // ================= CHECK EMAIL TỒN TẠI =================
        if (userService.findByEmail(email) != null) {
            req.setAttribute("error", "Email đã được sử dụng!");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
            return;
        }

        // ================= TẠO USER MỚI =================
        try {
            User u = new User();
            u.setUsername(username);
            u.setPassword(password);
            u.setFullname(fullname);
            u.setEmail(email);
            u.setIsAdmin(false);
            u.setActivated(true);

            userService.create(u);
//            userService.sendWelcomeEmail(u); // 👈 gửi email chào mừng

            // Thông báo thành công
            req.setAttribute("message", "Đăng ký thành công! Hãy đăng nhập.");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);

        } catch (Exception e) {
            req.setAttribute("error", "Có lỗi xảy ra khi đăng ký, vui lòng thử lại!");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
        }
    }
}
