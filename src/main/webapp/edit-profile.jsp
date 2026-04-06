<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thông tin cá nhân</title>

<link href="https://fonts.googleapis.com/css2?family=Plus+Jakarta+Sans:wght@400;500;600;700;800&display=swap" rel="stylesheet" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/layout/edit-profile.css">
</head>
<body>

<jsp:include page="header.jsp"/>

<div class="container">

    <!-- ĐÁNH DẤU TRANG ĐANG ĐỨNG ĐỂ SIDEBAR HIGHLIGHT -->
    <c:set var="page" value="edit-profile" />

    <!-- SIDEBAR -->
    <jsp:include page="sidebar-profile.jsp" />

    <!-- MAIN -->
    <div class="main">
        <h1>Cài đặt Tài khoản</h1>
        <p class="sub">Quản lý thông tin hồ sơ và cài đặt tài khoản của bạn.</p>

        <!-- SUCCESS -->
        <c:if test="${not empty message}">
            <div class="alert-success">${message}</div>
        </c:if>

        <!-- ERROR -->
        <c:if test="${not empty error}">
            <div class="alert-error">${error}</div>
        </c:if>

        <!-- FORM -->
        <form action="edit-profile" method="post">

            <div class="form-grid">
                <div class="form-group">
                    <label>Họ và Tên</label>
                    <!-- Lưu fullname vừa nhập nếu lỗi -->
                    <input type="text" name="fullname"
                           value="${param.fullname != null ? param.fullname : sessionScope.user.fullname}" 
                           required>
                </div>

                <div class="form-group">
                    <label>Tên người dùng</label>
                    <input type="text"
                           value="${sessionScope.user.username}" 
                           readonly>
                </div>
            </div>

            <div class="form-group">
                <label>Email</label>
                <input type="text" value="${sessionScope.user.email}" readonly>
            </div>

            <div class="action-row">
                <button type="button" class="btn btn-cancel" 
                        onclick="window.location='index'">Hủy</button>
                <button type="submit" class="btn btn-save">Lưu Thay Đổi</button>
            </div>

        </form>

    </div>
</div>

<jsp:include page="footer.jsp"/>

</body>
</html>
