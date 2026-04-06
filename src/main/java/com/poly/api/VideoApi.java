package com.poly.api;

import java.io.IOException;

import com.poly.model.Video;
import com.poly.service.VideoService;
import com.poly.util.RestIO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/api/videos/*")
public class VideoApi extends HttpServlet {

    private VideoService service = new VideoService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        String path = req.getPathInfo();

        // GET /api/videos  → lấy tất cả video
        if (path == null || path.equals("/") ) {
            RestIO.writeObject(resp, service.findAll());
            return;
        }

        // GET /api/videos/{id}
        try {
            Integer id = Integer.valueOf(path.substring(1));
            Video video = service.findById(id);

            RestIO.writeObject(resp, video);
        } catch (Exception e) {
            RestIO.writeEmptyObject(resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        Video video = RestIO.readObject(req, Video.class);
        Video saved = service.create(video);

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
            Video exist = service.findById(id);

            if (exist == null) {
                RestIO.writeEmptyObject(resp);
                return;
            }

            Video video = RestIO.readObject(req, Video.class);

            // Gắn ID để đảm bảo update đúng video
            video.setId(id);

            service.update(video);

            RestIO.writeObject(resp, video);

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
