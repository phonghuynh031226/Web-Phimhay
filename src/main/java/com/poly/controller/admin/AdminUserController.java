package com.poly.controller.admin;

import java.io.IOException;
import java.util.List;

import com.poly.model.User;
import com.poly.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/admin/users")
public class AdminUserController extends HttpServlet {

    private UserService service = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");
        String idStr  = req.getParameter("id");

        // ==== MẶC ĐỊNH: FORM TRỐNG ====
        User editUser = null;

        // ==== CHỈ LOAD KHI EDIT ====
        if ("edit".equals(action) && idStr != null) {
            editUser = service.findById(Integer.valueOf(idStr));
        }

        // GỬI SANG JSP bằng tên KHÔNG TRÙNG session
        req.setAttribute("editUser", editUser);

        // Load danh sách
        req.setAttribute("list", service.findAll());

        req.getRequestDispatcher("/admin/users.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String action   = req.getParameter("action");
        String idStr    = req.getParameter("id");

        String fullname = req.getParameter("fullname");
        String email    = req.getParameter("email");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String role     = req.getParameter("role");
        String active   = req.getParameter("active");

        // CREATE
        if ("create".equals(action)) {
            User u = new User();
            u.setUsername(username);
            u.setFullname(fullname);
            u.setEmail(email);
            u.setPassword(password);
            u.setIsAdmin(role.equals("admin"));
            u.setActivated(active.equals("active"));

            service.create(u);
        }

        // UPDATE
        if ("update".equals(action)) {
            Integer id = Integer.valueOf(idStr);
            User u = service.findById(id);

            if (u != null) {
                u.setFullname(fullname);
                u.setEmail(email);

                if (password != null && !password.isBlank()) {
                    u.setPassword(password);
                }

                u.setIsAdmin(role.equals("admin"));
                u.setActivated(active.equals("active"));
                service.update(u);
            }
        }

        // DELETE
        if ("delete".equals(action)) {
            service.delete(Integer.valueOf(idStr));
        }

        resp.sendRedirect("users");
    }
}
