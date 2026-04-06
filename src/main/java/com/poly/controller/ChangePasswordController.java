package com.poly.controller;

import java.io.IOException;

import com.poly.model.User;
import com.poly.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/change-password")
public class ChangePasswordController extends HttpServlet {

    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        // Chưa đăng nhập → chuyển login
        if (user == null) {
            resp.sendRedirect("login");
            return;
        }

        req.getRequestDispatcher("/change-password.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        // Nếu chưa login → redirect login
        if (user == null) {
            resp.sendRedirect("login");
            return;
        }

        // Nhận dữ liệu form
        String currentPass   = req.getParameter("currentPass");
        String newPass       = req.getParameter("newPass");
        String confirmPass   = req.getParameter("confirmPass");

        // Sai mật khẩu cũ
        if (!user.getPassword().equals(currentPass)) {
            req.setAttribute("error", "Mật khẩu cũ không đúng!");
            req.getRequestDispatcher("/change-password.jsp").forward(req, resp);
            return;
        }

        // Lỗi xác nhận
        if (!newPass.equals(confirmPass)) {
            req.setAttribute("error", "Xác nhận mật khẩu không khớp!");
            req.getRequestDispatcher("/change-password.jsp").forward(req, resp);
            return;
        }

        // Cập nhật DB
        userService.updatePassword(user.getUserId(), newPass);


        // Cập nhật session
        user.setPassword(newPass);
        session.setAttribute("user", user);

        req.setAttribute("message", "Đổi mật khẩu thành công!");
        req.getRequestDispatcher("/change-password.jsp").forward(req, resp);
    }
}
