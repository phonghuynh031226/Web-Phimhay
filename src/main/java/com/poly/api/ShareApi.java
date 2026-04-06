package com.poly.api;

import java.io.IOException;
import java.util.List;

import com.poly.model.Share;
import com.poly.model.User;
import com.poly.model.Video;
import com.poly.service.ShareService;
import com.poly.service.UserService;
import com.poly.service.VideoService;
import com.poly.util.RestIO;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/api/shares/*")
public class ShareApi extends HttpServlet {

    private ShareService shareService = new ShareService();
    private UserService userService = new UserService();
    private VideoService videoService = new VideoService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        String path = req.getPathInfo();

        // GET /api/shares → lấy tất cả share
        if (path == null || path.equals("/")) {
            List<Share> list = shareService.findAll();
            RestIO.writeObject(resp, list);
            return;
        }

        // GET /api/shares/{id}
        try {
            Integer id = Integer.valueOf(path.substring(1));
            Share s = shareService.findById(id);
            RestIO.writeObject(resp, s);

        } catch (Exception e) {
            RestIO.writeEmptyObject(resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        /*
            JSON format:
            {
                "userId": 3,
                "videoId": 4,
                "receiverEmail": "abc@gmail.com"
            }
        */

        Share input = RestIO.readObject(req, Share.class);

        if (input == null ||
            input.getUser() == null ||
            input.getVideo() == null ||
            input.getReceiverEmail() == null) {

            RestIO.writeEmptyObject(resp);
            return;
        }

        Integer uid = input.getUser().getUserId();
        Integer vid = input.getVideo().getId();
        String email = input.getReceiverEmail();

        User user = userService.findById(uid);
        Video video = videoService.findById(vid);

        if (user == null || video == null) {
            RestIO.writeEmptyObject(resp);
            return;
        }

        // Gọi ShareService để lưu share
        shareService.share(uid, vid, email);

        RestIO.writeObject(resp, input);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        String path = req.getPathInfo();

        try {
            Integer id = Integer.valueOf(path.substring(1));
            shareService.delete(id);      // cần thêm delete trong ShareService nếu chưa có
        } catch (Exception e) {
            // ignore
        }

        RestIO.writeEmptyObject(resp);
    }
}
