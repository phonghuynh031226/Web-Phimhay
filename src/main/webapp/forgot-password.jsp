<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>Quên mật khẩu</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/layout/forgot-password.css">
<link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined" rel="stylesheet" />
<link href="https://fonts.googleapis.com/css2?family=Plus+Jakarta+Sans:wght@400;500;700;900&display=swap" rel="stylesheet">

</head>
<body>



<div class="wrap">
    <div class="card">

        <p class="title">Khôi phục mật khẩu</p>
        <p class="subtitle">Nhập email bạn đã dùng để đăng ký tài khoản.</p>

        <!-- SUCCESS -->
        <c:if test="${not empty message}">
            <p class="success">${message}</p>
        </c:if>

        <!-- ERROR -->
        <c:if test="${not empty error}">
            <p class="error">${error}</p>
        </c:if>

        <form action="forgot-password" method="post" autocomplete="off">

            <label for="email">Địa chỉ Email</label>
            <div class="input-box">
                <span class="material-symbols-outlined">mail</span>
                <input class="input"
                       type="email"
                       name="email"
                       id="email"
                       placeholder="Nhập email của bạn"
                       value="${param.email}"
                       required />
            </div>

            <button class="btn" type="submit">Gửi mật khẩu</button>

        </form>

        <p class="back">
            Nhớ mật khẩu?
            <a href="login">Đăng nhập</a>
        </p>

    </div>
</div>

</body>
</html>
