package com.poly.service;

import java.util.Date;
import java.util.List;

import com.poly.dao.ShareDAO;
import com.poly.dao.UserDAO;
import com.poly.dao.VideoDAO;
import com.poly.model.Share;
import com.poly.model.User;
import com.poly.model.Video;

public class ShareService {

    private ShareDAO shareDao = new ShareDAO();
    private UserDAO userDao = new UserDAO();
    private VideoDAO videoDao = new VideoDAO();

    public void share(Integer userId, Integer videoId, String receiverEmail) {

        if (receiverEmail == null || receiverEmail.isBlank()) {
            throw new RuntimeException("Email người nhận không được để trống!");
        }

        // Load User
        User user = userDao.findById(userId);
        if (user == null) {
            throw new RuntimeException("User không tồn tại!");
        }

        // Load Video
        Video video = videoDao.findById(videoId.toString());
        if (video == null) {
            throw new RuntimeException("Video không tồn tại!");
        }

        // Tạo bản ghi Share
        Share share = new Share();
        share.setUser(user);
        share.setVideo(video);
        share.setReceiverEmail(receiverEmail);
        share.setShareDate(new Date());

        shareDao.create(share);

        // Tùy chọn: gửi email thật
        System.out.println("Đã gửi email chia sẻ đến: " + receiverEmail);
    }
    
    public List<Share> findAll() {
        return shareDao.findAll();
    }

    public Share findById(Integer id) {
        return shareDao.findById(id);
    }

    public void delete(Integer id) {
        shareDao.delete(id);
    }

}
