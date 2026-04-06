<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng ký</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/layout/register.css">
<link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined" rel="stylesheet" />

</head>
<body>

<div class="bg-wrapper">
    <img src="https://lh3.googleusercontent.com/aida-public/AB6AXuBGJ0RmjRW4jkGXg8cEzyUXEvwPvQRI-ygoIVUE_pupsJPzpdk8UPB3PsahucjRHnGRTAI5j6p1qU2X_r2yjEeAyA9SbxeTQJMqYaplNQ88MQHjYcBRdefAZDMsSkKgV00t4h50WlKw_MRKLvD3_hLxyhc7jVqZfrmjx5LmhBrZqGVmSgiPvNsZBUIr4FjEyiRheRst1QgQQ7CaaqZjfZwvHY1_Xib1WzJK7Ktq2qBkxpaZLQzjn40eWo_HgnsOBIuU76i5GlwhWHU"
         alt="Background">
    <div class="bg-overlay"></div>
</div>

<div class="register-wrapper">
    <div class="register-card">

        <div class="logo">PHIMHAY</div>

        <h1 class="title">Tạo tài khoản</h1>
        <p class="subtitle">
            Tham gia cùng chúng tôi để khám phá thế giới phim ảnh không giới hạn.
        </p>

        <form action="register" method="post" autocomplete="off">

            <label for="name">Tên của bạn</label>
            <div class="input-group">
                <input type="text" name="fullname" id="name"
                       placeholder="Nhập tên của bạn"
                       value="${param.fullname}"
                       required>
                <span class="material-symbols-outlined">person</span>
            </div>

            <label for="email">Email</label>
            <div class="input-group">
                <input type="email" name="email" id="email"
                       placeholder="Nhập email của bạn"
                       value="${param.email}"
                       required>
                <span class="material-symbols-outlined">mail</span>
            </div>

            <label for="username">Tên đăng nhập</label>
            <div class="input-group">
                <input type="text" name="username" id="username"
                       placeholder="Tạo tên đăng nhập"
                       value="${param.username}"
                       required>
                <span class="material-symbols-outlined">badge</span>
            </div>

            <label for="password">Mật khẩu</label>
            <div class="input-group">
                <input type="password" name="password" id="password"
                       placeholder="Nhập mật khẩu"
                       required>
                <span id="togglePass1" class="material-symbols-outlined">visibility_off</span>
            </div>

            <label for="confirm-password">Xác nhận mật khẩu</label>
            <div class="input-group">
                <input type="password" name="confirm" id="confirm-password"
                       placeholder="Nhập lại mật khẩu"
                       required>
                <span id="togglePass2" class="material-symbols-outlined">visibility_off</span>
            </div>

            <!-- ERROR -->
            <c:if test="${not empty error}">
                <p class="error-msg">${error}</p>
            </c:if>

            <!-- SUCCESS -->
            <c:if test="${not empty message}">
                <p class="success-msg">${message}</p>
            </c:if>

            <button type="submit" class="btn-register">Đăng ký</button>
        </form>

        <p class="bottom-text">
            Đã có tài khoản?
            <a href="login">Đăng nhập ngay</a>
        </p>

    </div>
</div>

<script>
    // Toggle password
    const pass1 = document.getElementById("password");
    const pass2 = document.getElementById("confirm-password");
    const t1 = document.getElementById("togglePass1");
    const t2 = document.getElementById("togglePass2");

    function toggle(input, icon) {
        input.type = input.type === "password" ? "text" : "password";
        icon.innerText = input.type === "password" ? "visibility_off" : "visibility";
    }

    t1.onclick = () => toggle(pass1, t1);
    t2.onclick = () => toggle(pass2, t2);
</script>

</body>
</html>
