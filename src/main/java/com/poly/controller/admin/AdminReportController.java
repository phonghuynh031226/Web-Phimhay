package com.poly.controller.admin;

import java.io.IOException;
import java.util.List;

import com.poly.dao.ReportDAO;
import com.poly.dao.VideoDAO;
import com.poly.model.Favorite;
import com.poly.model.Video;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/admin/reports")
public class AdminReportController extends HttpServlet {

    private ReportDAO reportDao = new ReportDAO();
    private VideoDAO videoDao = new VideoDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String type = req.getParameter("type");
        String videoId = req.getParameter("videoId");

        /* ===============================
           TAB 1: SUMMARY (Thống kê tổng)
        =================================*/
        if (type == null || type.equals("summary")) {

            List<Object[]> summary = reportDao.getFavoriteStats();
            req.setAttribute("summary", summary);

            req.getRequestDispatcher("/admin/reports.jsp").forward(req, resp);
            return;
        }

        /* ===============================
           TAB 2: DETAIL (Người dùng thích)
        =================================*/
        if (type.equals("detail")) {

            List<Video> videos = videoDao.findAll();
            req.setAttribute("videos", videos);

            // Nếu chưa chọn video → chọn video đầu tiên
            if (videoId == null && !videos.isEmpty()) {
                videoId = String.valueOf(videos.get(0).getId());
            }

            if (videoId != null) {
                int vid = Integer.parseInt(videoId);
                List<Favorite> detail = reportDao.getUsersByVideo(vid);
                req.setAttribute("detail", detail);
            }

            req.getRequestDispatcher("/admin/reports.jsp").forward(req, resp);
            return;
        }

        // fallback
        req.getRequestDispatcher("/admin/reports.jsp").forward(req, resp);
    }
}
