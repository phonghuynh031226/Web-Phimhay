<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layout/sidebar.css">
<div class="sidebar">
    <div class="profile-box">
        <div>
            <h3>${sessionScope.user.fullname}</h3>
            <p style="color:#bbb; font-size:14px;">${sessionScope.user.email}</p>
        </div>
    </div>

    <a href="edit-profile">
        <div class="sidebar-item ${page eq 'profile' ? 'active' : ''}">
            <span></span> Thông tin cá nhân
        </div>
    </a>

    <a href="change-password">
        <div class="sidebar-item ${page eq 'password' ? 'active' : ''}">
            <span></span> Mật khẩu
        </div>
    </a>

    <a href="index">
        <div class="sidebar-item">
            <span></span> Quay lại
        </div>
    </a>

    <div style="border-top:1px solid #333; margin-top:20px; padding-top:15px;">
        <a href="logout">
            <div class="sidebar-item">
                <span></span> Đăng xuất
            </div>
        </a>
    </div>
</div>
