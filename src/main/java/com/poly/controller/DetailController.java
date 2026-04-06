package com.poly.controller;

import java.io.IOException;
import java.util.List;

import com.poly.model.User;
import com.poly.model.Video;
import com.poly.service.FavoriteService;
import com.poly.service.VideoService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/detail")
public class DetailController extends HttpServlet {

    private VideoService videoService = new VideoService();
    private FavoriteService favoriteService = new FavoriteService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String id = req.getParameter("id");

        // Không có id → về trang chủ
        if (id == null || id.isEmpty()) {
            resp.sendRedirect("index");
            return;
        }

        // Lấy video
        Video video = videoService.findById(id);
        if (video == null) {
            resp.sendRedirect("index");
            return;
        }

        // Tăng lượt xem
        videoService.increaseView(video);

        // Lưu history xem video bằng cookie
        saveRecentVideo(req, resp, id);

        // Lấy video đề xuất
        List<Video> suggest = videoService.getSuggest(id);

        // KIỂM TRA LIKE / UNLIKE
        boolean isFavorite = false;

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        if (user != null) {
            isFavorite = favoriteService.isFavorite(user.getUserId(), video.getId());
        }

        // Truyền data
        req.setAttribute("video", video);
        req.setAttribute("suggest", suggest);
        req.setAttribute("isFavorite", isFavorite);   // Gửi trạng thái LIKE vào JSP

        req.getRequestDispatcher("/detail.jsp").forward(req, resp);
    }

    // ========== Lưu vào cookie các video đã xem ==========
    private void saveRecentVideo(HttpServletRequest req, HttpServletResponse resp, String id) {

        Cookie[] cookies = req.getCookies();
        String list = id;   // mặc định chỉ có id hiện tại

        if (cookies != null) {
            for (Cookie ck : cookies) {
                if ("recent".equals(ck.getName())) {
                    String old = ck.getValue();
                    if (old != null) {
                        old = old.replace(",", "-").replace(" ", "");
                    }

                    if (old != null && !old.isEmpty()) {
                        if (!old.contains(id)) {
                            list = old + "-" + id;
                        } else {
                            list = old;
                        }
                    }
                }
            }
        }

        Cookie recent = new Cookie("recent", list);
        recent.setMaxAge(60 * 60 * 24 * 7); // 7 ngày
        recent.setPath(req.getContextPath());
        resp.addCookie(recent);
    }
}
