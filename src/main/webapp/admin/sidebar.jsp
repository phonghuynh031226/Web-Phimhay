<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<aside class="sidebar">

    <!-- LOGO -->
    <div class="sidebar-logo">
        <div class="sidebar-logo-icon">
            <span class="material-symbols-outlined">movie</span>
        </div>
        <h1 class="sidebar-logo-title">ASM Admin</h1>
    </div>

    <!-- MENU -->
    <div class="sidebar-menu">

        <a class="${page == 'dashboard' ? 'active' : ''}"
           href="${pageContext.request.contextPath}/admin/index">
            <span class="material-symbols-outlined">dashboard</span>
            <span class="nav-label">Bảng điều khiển</span>
        </a>

        <a class="${page == 'report' ? 'active' : ''}"
           href="${pageContext.request.contextPath}/admin/reports?type=summary">
            <span class="material-symbols-outlined">bar_chart</span>
            <span class="nav-label">Báo cáo</span>
        </a>

        <a class="${page == 'videos' ? 'active' : ''}"
           href="${pageContext.request.contextPath}/admin/videos">
            <span class="material-symbols-outlined">theaters</span>
            <span class="nav-label">Quản lý Video</span>
        </a>

        <a class="${page == 'users' ? 'active' : ''}"
           href="${pageContext.request.contextPath}/admin/users">
            <span class="material-symbols-outlined">group</span>
            <span class="nav-label">Người dùng</span>
        </a>
    </div>

    <!-- FOOTER -->
    <div class="sidebar-footer">
        <a href="${pageContext.request.contextPath}/index" class="sidebar-logout">
            <span class="material-symbols-outlined">arrow_back</span>
            <span class="sidebar-logout-label">Trang người dùng</span>
        </a>

        <a href="${pageContext.request.contextPath}/logout" class="sidebar-logout">
            <span class="material-symbols-outlined">logout</span>
            <span class="sidebar-logout-label">Đăng xuất</span>
        </a>
    </div>

</aside>
