package com.poly.controller;

import java.io.IOException;
import java.util.List;

import com.poly.model.User;
import com.poly.model.Favorite;
import com.poly.service.FavoriteService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/favorite")
public class FavoriteController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private FavoriteService service = new FavoriteService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        // Chưa đăng nhập -> quay về login
        if (user == null) {
            resp.sendRedirect("login");
            return;
        }

        String action = req.getParameter("action");
        String videoIdParam = req.getParameter("videoId");
        String idParam = req.getParameter("id");      // fallback cho URL kiểu cũ: favorite?id=5

        // Ưu tiên videoId, nếu null thì dùng id (để không bị null khi lỡ dùng link cũ)
        String rawVideoId = (videoIdParam != null && !videoIdParam.isEmpty())
                ? videoIdParam
                : idParam;

        // ==== XỬ LÝ LIKE / UNLIKE BẰNG GET ====
        if (action != null && rawVideoId != null) {
            Integer videoId = Integer.valueOf(rawVideoId);
            String from = req.getParameter("from"); // lấy nguồn gọi
            if ("like".equals(action)) {
                // Thêm vào yêu thích
                service.addFavorite(user.getUserId(), videoId);

                // Sau khi thích, quay lại trang chi tiết video
                resp.sendRedirect("detail?id=" + videoId);
                return;
            }             
            
            else if ("unlike".equals(action)) {
                service.removeFavorite(user.getUserId(), videoId);

                // Xử lý redirect theo nguồn
                if ("detail".equals(from)) {
                    resp.sendRedirect("detail?id=" + videoId);
                } else {
                    resp.sendRedirect("favorite");
                }
                return;
            }
        }


        // ==== MẶC ĐỊNH: HIỂN THỊ DANH SÁCH VIDEO YÊU THÍCH CỦA USER ====
        List<Favorite> list = service.findByUser(user.getUserId());
        req.setAttribute("list", list);
        req.getRequestDispatcher("/favorites.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        // Chưa đăng nhập -> quay về login
        if (user == null) {
            resp.sendRedirect("login");
            return;
        }

        String action = req.getParameter("action");
        String videoIdParam = req.getParameter("videoId");

        // Không có videoId thì quay lại danh sách yêu thích
        if (videoIdParam == null || videoIdParam.isEmpty()) {
            resp.sendRedirect("favorite");
            return;
        }

        Integer videoId = Integer.valueOf(videoIdParam);

        if ("like".equals(action)) {
            service.addFavorite(user.getUserId(), videoId);
        } else if ("unlike".equals(action)) {
            service.removeFavorite(user.getUserId(), videoId);
        }

        // POST thì mình cho quay lại danh sách yêu thích
        resp.sendRedirect("favorite");
    }
}
