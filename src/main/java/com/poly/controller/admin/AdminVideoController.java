package com.poly.controller.admin;

import java.io.IOException;
import java.util.List;

import com.poly.model.Video;
import com.poly.service.VideoService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/admin/videos")
public class AdminVideoController extends HttpServlet {

    private VideoService service = new VideoService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");
        String idStr  = req.getParameter("id");

        // ====================== DELETE (SweetAlert gửi GET) ======================
        if ("delete".equals(action) && idStr != null) {
            try {
                Integer id = Integer.valueOf(idStr);

                // Xóa favorite trước rồi xóa video
                service.deleteVideoAndFavorites(id);

            } catch (Exception e) {
                e.printStackTrace(); // xem log trong console Tomcat
            }

            resp.sendRedirect("videos");
            return; // DỪNG doGet sau khi xoá
        }

        // ====================== EDIT: load 1 video lên form ======================
        if ("edit".equals(action) && idStr != null) {
            try {
                Integer id = Integer.valueOf(idStr);
                Video v = service.findById(id);
                req.setAttribute("video", v);
            } catch (Exception e) {
                resp.sendRedirect("videos");
                return;
            }
        }

        // ====================== LOAD DANH SÁCH VIDEO ============================
        List<Video> list = service.findAll();
        req.setAttribute("list", list);

        req.getRequestDispatcher("/admin/videos.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String action      = req.getParameter("action");
        String title       = req.getParameter("title");
        String description = req.getParameter("description");
        String poster      = req.getParameter("poster");
        String youtubeId   = req.getParameter("youtubeId");
        String idStr       = req.getParameter("id");

        // ====================== CREATE =========================================
        if ("create".equals(action)) {
            Video v = new Video();
            v.setTitle(title);
            v.setPoster(poster);
            v.setDescription(description);
            v.setYoutubeId(youtubeId);
            service.create(v);
        }

        // ====================== UPDATE =========================================
        if ("update".equals(action) && idStr != null) {
            try {
                Integer id = Integer.valueOf(idStr);
                Video v = service.findById(id);

                if (v != null) {
                    v.setTitle(title);
                    v.setPoster(poster);
                    v.setDescription(description);
                    v.setYoutubeId(youtubeId);
                    service.update(v);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // ❌ KHÔNG xử lý delete trong POST nữa
        resp.sendRedirect("videos");
    }
}
