package com.poly.api;

import java.io.IOException;

import com.poly.model.User;
import com.poly.service.UserService;
import com.poly.util.RestIO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/api/users/*")
public class UserApi extends HttpServlet {

    private UserService service = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        String path = req.getPathInfo();

        // GET /api/users  → lấy tất cả user
        if (path == null || path.equals("/")) {
            RestIO.writeObject(resp, service.findAll());
            return;
        }

        // GET /api/users/{id}
        try {
            Integer id = Integer.valueOf(path.substring(1));
            User user = service.findById(id);
            RestIO.writeObject(resp, user);

        } catch (Exception e) {
            RestIO.writeEmptyObject(resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        User user = RestIO.readObject(req, User.class);

        // Tạo user mới
        User saved = service.create(user);

        RestIO.writeObject(resp, saved);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        String path = req.getPathInfo();
        if (path == null || path.equals("/")) {
            RestIO.writeEmptyObject(resp);
            return;
        }

        try {
            Integer id = Integer.valueOf(path.substring(1));

            // user trong DB
            User exist = service.findById(id);
            if (exist == null) {
                RestIO.writeEmptyObject(resp);
                return;
            }

            // user update từ JSON
            User input = RestIO.readObject(req, User.class);

            // Gắn ID để update đúng user
            input.setUserId(id);

            User updated = service.update(input);

            RestIO.writeObject(resp, updated);

        } catch (Exception e) {
            RestIO.writeEmptyObject(resp);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        String path = req.getPathInfo();

        try {
            Integer id = Integer.valueOf(path.substring(1));
            service.delete(id);

        } catch (Exception e) {
            // ignore
        }

        RestIO.writeEmptyObject(resp);
    }
}
