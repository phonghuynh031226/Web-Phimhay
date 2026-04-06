package com.poly.controller;

import java.io.IOException;

import com.poly.model.User;
import com.poly.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/forgot-password")
public class ForgotPasswordController extends HttpServlet {

    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Nếu đã đăng nhập rồi → không cần reset password
        if (req.getSession().getAttribute("user") != null) {
            resp.sendRedirect("index");
            return;
        }

        req.getRequestDispatcher("/forgot-password.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String email = req.getParameter("email");

        if (email == null || email.trim().isEmpty()) {
            req.setAttribute("error", "Vui lòng nhập email!");
            req.getRequestDispatcher("/forgot-password.jsp").forward(req, resp);
            return;
        }

        User user = userService.findByEmail(email);

        if (user == null) {
            req.setAttribute("error", "Email không tồn tại trong hệ thống!");
            req.getRequestDispatcher("/forgot-password.jsp").forward(req, resp);
            return;
        }

        // Gửi mật khẩu qua email
        userService.sendPasswordEmail(user);

        req.setAttribute("message", "Mật khẩu đã được gửi đến email của bạn!");
        req.setAttribute("emailSent", true);   // Dùng để disable input nếu cần

        req.getRequestDispatcher("/forgot-password.jsp").forward(req, resp);
    }
}
