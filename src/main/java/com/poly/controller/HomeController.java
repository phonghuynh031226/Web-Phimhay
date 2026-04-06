package com.poly.controller;

import java.io.IOException;
import java.util.List;

import com.poly.model.Video;
import com.poly.service.VideoService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet({"/index", "/home", ""})
public class HomeController extends HttpServlet {

    private VideoService videoService = new VideoService();
    private static final int PAGE_SIZE = 8;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Lấy tổng số video
        int totalVideos = videoService.countVideos();
        int totalPage = (int) Math.ceil(totalVideos * 1.0 / PAGE_SIZE);

        // Lấy trang hiện tại
        String pageParam = req.getParameter("page");
        int page = (pageParam == null) ? 1 : Integer.valueOf(pageParam);

        // Giới hạn trang không vượt quá totalPage
        if (page < 1) page = 1;
        if (page > totalPage) page = totalPage;

        // Lấy danh sách video theo trang
        List<Video> videos = videoService.getPage(page, PAGE_SIZE);

        // Đưa dữ liệu sang JSP
        req.setAttribute("videos", videos);
        req.setAttribute("page", page);
        req.setAttribute("totalPage", totalPage);

        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
