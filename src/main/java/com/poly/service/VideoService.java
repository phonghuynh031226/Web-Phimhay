package com.poly.service;

import java.util.List;

import com.poly.dao.VideoDAO;
import com.poly.dao.FavoriteDAO;
import com.poly.model.Video;

public class VideoService {

    private VideoDAO dao = new VideoDAO();
    private FavoriteDAO favoriteDao = new FavoriteDAO();

    public List<Video> findAll() {
        return dao.findAll();
    }

    public List<Video> getActiveVideos() {
        return dao.findActive();
    }

    public Video findById(Integer id) {
        return dao.findById(id.toString());
    }

    public Video findById(String id) {
        return dao.findById(id);
    }

    public List<Video> search(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return getActiveVideos();
        }
        return dao.findByKeyword(keyword);
    }

    public List<Video> getPage(int page, int size) {
        return dao.findPage(page, size);
    }

    public int countVideos() {
        return dao.count();
    }

    public void increaseView(Video video) {
        if (video == null) return;

        Integer views = video.getViews();
        if (views == null) views = 0;
        video.setViews(views + 1);
        dao.update(video);
    }

    public List<Video> getSuggest(String videoId) {
        return dao.findSuggest(videoId);
    }

    public Video create(Video video) {
        return dao.create(video);
    }

    public Video update(Video video) {
        return dao.update(video);
    }

    // ================= XOÁ VIDEO + FAVORITE LIÊN QUAN =================
    public void deleteVideoAndFavorites(Integer videoId) {

        // 1. Xóa hết Favorite của video
        favoriteDao.deleteByVideo(videoId);

        // 2. Xóa video
        dao.delete(videoId);
    }

    @Deprecated
    public void delete(Integer id) {
        deleteVideoAndFavorites(id);
    }
}
