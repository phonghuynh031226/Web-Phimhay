package com.poly.controller;

import java.io.IOException;

import com.poly.model.User;
import com.poly.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    private UserService service = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Nếu đã đăng nhập → quay về trang chủ
        if (req.getSession().getAttribute("user") != null) {
            resp.sendRedirect("index");
            return;
        }

        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = service.login(username, password);

        if (user == null) {
            req.setAttribute("error", "Sai tài khoản hoặc mật khẩu!");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
            return;
        }

        // Đăng nhập thành công
        HttpSession session = req.getSession();
        session.setAttribute("user", user);

        // Nếu có trang bảo mật cần quay lại
        String backUri = (String) session.getAttribute("security-uri");
        if (backUri != null) {
            session.removeAttribute("security-uri"); 
            resp.sendRedirect(backUri);
            return;
        }

        // Không có trang nào → về index
        resp.sendRedirect("index");
    }
}
