package com.poly.api;

import java.io.IOException;
import java.util.List;

import com.poly.model.Favorite;
import com.poly.service.FavoriteService;
import com.poly.service.UserService;
import com.poly.service.VideoService;
import com.poly.util.RestIO;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/api/favorites/*")
public class FavoriteApi extends HttpServlet {

    private FavoriteService favoriteService = new FavoriteService();
    private UserService userService = new UserService();
    private VideoService videoService = new VideoService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        // GET /api/favorites → lấy tất cả Favorite
        List<Favorite> list = favoriteService.findAll();
        RestIO.writeObject(resp, list);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        /*
            JSON input example:
            {
                "user":  {"userId": 3},
                "video": {"id": 5}
            }
        */

        Favorite input = RestIO.readObject(req, Favorite.class);

        if (input == null ||
            input.getUser() == null ||
            input.getVideo() == null) {

            RestIO.writeEmptyObject(resp);
            return;
        }

        Integer uid = input.getUser().getUserId();
        Integer vid = input.getVideo().getId();

        favoriteService.addFavorite(uid, vid);

        RestIO.writeObject(resp, input);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        // DELETE /api/favorites?userId=3&videoId=5
        String uidStr = req.getParameter("userId");
        String vidStr = req.getParameter("videoId");

        try {
            Integer uid = Integer.valueOf(uidStr);
            Integer vid = Integer.valueOf(vidStr);

            favoriteService.removeFavorite(uid, vid);

        } catch (Exception e) {
            // ignore lỗi nhỏ
        }

        RestIO.writeEmptyObject(resp);
    }
}
