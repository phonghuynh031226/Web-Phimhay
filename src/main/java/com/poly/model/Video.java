package com.poly.model;

import java.io.Serializable;

import jakarta.persistence.*;

@Entity
@Table(name = "Videos")
public class Video implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VideoID")
    private Integer id;

    @Column(name = "Title", nullable = false, length = 200)
    private String title;

    @Column(name = "Poster", nullable = false, length = 200)
    private String poster;

    @Lob
    @Column(name = "Description")
    private String description;

    @Column(name = "Views", nullable = false)
    private Integer views = 0;

    @Column(name = "Active", nullable = false)
    private Boolean active = true;

    @Column(name = "YoutubeID", nullable = false, length = 50)
    private String youtubeId;

    // ===== Constructor =====
    public Video() {}

    public Video(String title, String poster, String description, String youtubeId) {
        this.title = title;
        this.poster = poster;
        this.description = description;
        this.youtubeId = youtubeId;
    }

    // ===== Getter & Setter =====
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getYoutubeId() {
        return youtubeId;
    }

    public void setYoutubeId(String youtubeId) {
        this.youtubeId = youtubeId;
    }
}
