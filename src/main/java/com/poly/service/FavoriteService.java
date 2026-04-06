package com.poly.service;

import java.util.List;
import java.util.Date;

import com.poly.dao.FavoriteDAO;
import com.poly.dao.UserDAO;
import com.poly.dao.VideoDAO;
import com.poly.model.Favorite;
import com.poly.model.User;
import com.poly.model.Video;

public class FavoriteService {

    private FavoriteDAO favoriteDao = new FavoriteDAO();
    private UserDAO userDao = new UserDAO();
    private VideoDAO videoDao = new VideoDAO();

    // Lấy danh sách favorite của 1 user
    public List<Favorite> findByUser(Integer userId) {
        return favoriteDao.findByUser(userId);
    }

    // Thêm vào yêu thích
    public void addFavorite(Integer userId, Integer videoId) {

        User user = userDao.findById(userId);
        Video video = videoDao.findById(videoId.toString());

        if (user == null || video == null) {
            throw new RuntimeException("User hoặc Video không tồn tại!");
        }

        // Không cho tạo trùng
        if (favoriteDao.findOne(userId, videoId) != null)
            return;

        Favorite fav = new Favorite();
        fav.setUser(user);
        fav.setVideo(video);
        fav.setLikedDate(new Date());

        favoriteDao.create(fav);
    }

    // Bỏ thích
    public void removeFavorite(Integer userId, Integer videoId) {
        favoriteDao.deleteFavorite(userId, videoId);
    }

    // Kiểm tra user đã thích video chưa
    public boolean isFavorite(Integer userId, Integer videoId) {
        return favoriteDao.findOne(userId, videoId) != null;
    }

    // Lấy tất cả favorite
    public List<Favorite> findAll() {
        return favoriteDao.findAll();
    }
}
