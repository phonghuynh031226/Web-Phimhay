package com.poly.controller;

import java.io.IOException;

import com.poly.model.User;
import com.poly.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/edit-profile")
public class ProfileController extends HttpServlet {

    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        // Chưa đăng nhập → bắt đăng nhập
        if (user == null) {
            session.setAttribute("security-uri", "/edit-profile");
            resp.sendRedirect("login");
            return;
        }

        req.getRequestDispatcher("/edit-profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        // Nếu chưa login → không cho update
        if (user == null) {
            resp.sendRedirect("login");
            return;
        }

        String fullname = req.getParameter("fullname");

        // Kiểm tra fullname rỗng
        if (fullname == null || fullname.trim().isEmpty()) {
            req.setAttribute("error", "Họ tên không được để trống!");
            req.getRequestDispatcher("/edit-profile.jsp").forward(req, resp);
            return;
        }

        try {
            // Cập nhật databases
            userService.updateProfile(user.getUserId(), fullname);


            // Cập nhật lại session
            user.setFullname(fullname);
            session.setAttribute("user", user);

            req.setAttribute("message", "Cập nhật thông tin thành công!");

        } catch (Exception e) {
            req.setAttribute("error", "Có lỗi xảy ra khi cập nhật!");
        }

        req.getRequestDispatcher("/edit-profile.jsp").forward(req, resp);
    }
}
