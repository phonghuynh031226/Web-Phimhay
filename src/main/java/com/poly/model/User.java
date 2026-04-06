package com.poly.model;

import java.io.Serializable;

import jakarta.persistence.*;

@Entity
@Table(name = "Users")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserID")
    private Integer userId;

    @Column(name = "Username", nullable = false, length = 50)
    private String username;

    @Column(name = "Password", nullable = false, length = 100)
    private String password;

    @Column(name = "Fullname", nullable = false, length = 100)
    private String fullname;

    @Column(name = "Email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "IsAdmin", nullable = false)
    private Boolean isAdmin = false;

    @Column(name = "Activated", nullable = false)
    private Boolean activated = true;

    // ===== CONSTRUCTOR =====
    public User() {}

    public User(Integer userId, String username, String password, String fullname,
                String email, Boolean isAdmin, Boolean activated) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.email = email;
        this.isAdmin = isAdmin;
        this.activated = activated;
    }

    // ===== GETTER & SETTER =====
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }
    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Boolean getActivated() {
        return activated;
    }
    public void setActivated(Boolean activated) {
        this.activated = activated;
    }
}
