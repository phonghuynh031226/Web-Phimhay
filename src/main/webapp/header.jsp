<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/layout/header.css">
<link rel="stylesheet" 
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">

<header>
    <div class="logo-area">
        <h2><a href="index" style="text-decoration:none; color:white;">PhimHay</a></h2>
    </div>

    <nav class="menu">
        <a href="index">Trang chủ</a>

        <c:choose>
            <c:when test="${empty sessionScope.user}">
                <a href="login">Đăng Nhập</a>
            </c:when>
            <c:otherwise>
                <a href="favorite">Video yêu thích</a>
            </c:otherwise>
        </c:choose>

        <!-- Admin -->
        <c:if test="${not empty sessionScope.user and sessionScope.user.isAdmin}">
            <a href="admin/index">Quản trị</a>
        </c:if>
    </nav>

    <div class="header-right">

        <div class="search-box">
            <form action="index" method="get">
                <input type="text" name="keyword" placeholder="Tìm kiếm phim..." />
            </form>
        </div>

        <div class="icon-btn"><i class="fa-solid fa-bell"></i></div>

        <!-- Nếu chưa login -->
        
        <c:if test="${empty sessionScope.user}">
		    <a href="login">
		        <i class="fa-regular fa-user" style="font-size:22px; color:white;"></i>
		    </a>
		</c:if>
        

        <!-- Nếu đã login -->
        <c:if test="${not empty sessionScope.user}">
            <a href="edit-profile">
                <div class="avatar"
                    style="background-image:url('https://i.pravatar.cc/60?u=${sessionScope.user.userId}');">
                </div>
            </a>

            <a href="logout" style="margin-left:15px; color:#fff;">Đăng xuất</a>
        </c:if>

    </div>
</header>
