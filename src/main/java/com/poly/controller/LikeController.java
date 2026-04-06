package com.poly.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.poly.service.FavoriteService;
import com.poly.model.User;

@WebServlet("/like")
public class LikeController extends HttpServlet {

    private FavoriteService service = new FavoriteService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String videoId = req.getParameter("id");

        // Lấy user từ session
        User user = (User) req.getSession().getAttribute("user");

        // Chưa đăng nhập → chuyển sang login
        if (user == null) {
            resp.sendRedirect("login");
            return;
        }

        // LIKE VIDEO ĐÚNG CÁCH
        service.addFavorite(user.getUserId(), Integer.valueOf(videoId));

        // Quay lại trang detail
        resp.sendRedirect("detail?id=" + videoId);
    }
}
