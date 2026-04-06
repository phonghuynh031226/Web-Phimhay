package com.poly.controller.admin;

import com.poly.model.Video;
import com.poly.service.VideoService;
import com.poly.service.UserService;
import com.poly.service.FavoriteService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/index")
public class AdminDashboardController extends HttpServlet {

    private VideoService videoService = new VideoService();
    private UserService userService = new UserService();
    private FavoriteService favoriteService = new FavoriteService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // ======= LẤY TOÀN BỘ VIDEO =======
        List<Video> allVideos = videoService.findAll();
        int totalVideos = allVideos.size();

        // ======= TỔNG LƯỢT XEM =======
        long totalViews = allVideos.stream()
                .filter(v -> v.getViews() != null)
                .mapToLong(Video::getViews)
                .sum();

        // ======= TỔNG USERS & FAVORITES =======
        int totalUsers = userService.findAll().size();
        int totalFavorites = favoriteService.findAll().size();

        // ======= SẮP XẾP VIDEO MỚI NHẤT =======
        allVideos.sort((v1, v2) -> v2.getId().compareTo(v1.getId()));

        // ======= GỬI TẤT CẢ VIDEO CHO DATATABLES =======
        List<Video> latestVideos = allVideos;

        // ======= TRUYỀN DỮ LIỆU SANG VIEW =======
        req.setAttribute("totalVideos", totalVideos);
        req.setAttribute("totalUsers", totalUsers);
        req.setAttribute("totalViews", totalViews);
        req.setAttribute("totalFavorites", totalFavorites);
        req.setAttribute("latestVideos", latestVideos);

        req.getRequestDispatcher("/admin/index.jsp").forward(req, resp);
    }
}
