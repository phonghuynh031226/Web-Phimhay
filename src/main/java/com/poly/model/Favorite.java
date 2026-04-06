package com.poly.model;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "Favorites")
public class Favorite implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FavID")
    private Integer favId;

    @ManyToOne(fetch = FetchType.LAZY)   // tối ưu hiệu năng
    @JoinColumn(name = "UserID")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VideoID")
    private Video video;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LikedDate", nullable = false)
    private Date likedDate = new Date();

    // ===== GETTER - SETTER =====

    public Integer getFavId() {
        return favId;
    }

    public void setFavId(Integer favId) {
        this.favId = favId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public Date getLikedDate() {
        return likedDate;
    }

    public void setLikedDate(Date likedDate) {
        this.likedDate = likedDate;
    }
}
