package com.poly.controller;

import java.io.IOException;

import com.poly.model.User;
import com.poly.model.Video;
import com.poly.service.ShareService;
import com.poly.service.VideoService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/share")
public class ShareController extends HttpServlet {

    private VideoService videoService = new VideoService();
    private ShareService shareService = new ShareService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        // Phải đăng nhập mới được share
        if (user == null) {
            session.setAttribute("security-uri", "/share?id=" + req.getParameter("id"));
            resp.sendRedirect("login");
            return;
        }

        // Lấy id video
        String id = req.getParameter("id");

        if (id == null || id.isBlank()) {
            resp.sendRedirect("index");
            return;
        }

        // Lấy video từ DB
        Video video = videoService.findById(id);
        if (video == null) {
            resp.sendRedirect("index");
            return;
        }

        req.setAttribute("video", video);
        req.getRequestDispatcher("/share.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            resp.sendRedirect("login");
            return;
        }

        String videoIdRaw = req.getParameter("videoId");
        String email = req.getParameter("email");

        // Kiểm tra dữ liệu
        if (videoIdRaw == null || email == null || email.isBlank()) {
            req.setAttribute("error", "Vui lòng nhập email người nhận!");
            req.getRequestDispatcher("/share.jsp").forward(req, resp);
            return;
        }

        Integer videoId = Integer.valueOf(videoIdRaw);
        Integer userId = user.getUserId();

        // Gọi service để share
        try {
            shareService.share(userId, videoId, email);
            req.setAttribute("message", "Chia sẻ thành công!");
        } catch (Exception e) {
            req.setAttribute("error", "Không thể chia sẻ! Vui lòng thử lại.");
        }

        // Load lại video
        Video video = videoService.findById(videoIdRaw);
        req.setAttribute("video", video);

        req.getRequestDispatcher("/share.jsp").forward(req, resp);
    }
}
