package com.poly.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/logout")
public class LogoutController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        if (session != null) {
            session.removeAttribute("user");         // Xóa user đã đăng nhập
            session.removeAttribute("security-uri"); // Xóa trang bảo mật nếu có
        }

        resp.sendRedirect("login"); // Chuyển về login
    }
}
