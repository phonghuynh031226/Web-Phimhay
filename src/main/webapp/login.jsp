<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng nhập</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/layout/login.css">
</head>
<body>

<div class="page">
    <div class="page-bg">
        <div class="page-bg-image"></div>
        <div class="page-bg-overlay"></div>
    </div>

    <div class="page-inner">
        <div class="login-card">

            <div class="login-header">
                <svg class="login-logo" viewBox="0 0 24 24" fill="currentColor">
                    <path d="M6.36881 ..."></path>
                    <path d="M12 11.9998 ..."></path>
                </svg>
                <h1 class="login-title">ĐĂNG NHẬP</h1>
            </div>

            <!-- SUCCESS (VD: sau khi đăng ký) -->
            <c:if test="${not empty message}">
                <p class="success">${message}</p>
            </c:if>

            <!-- ERROR -->
            <c:if test="${not empty error}">
                <p class="error">${error}</p>
            </c:if>

            <form method="post" action="login" class="login-form">

                <div class="form-group">
                    <label class="form-label">Tên đăng nhập</label>
                    <input type="text"
                           name="username"
                           class="form-input"
                           placeholder="Nhập tên đăng nhập"
                           value="${param.username}"
                           required>
                </div>

                <div class="form-group">
                    <label class="form-label">Mật khẩu</label>
                    <div class="password-wrapper">
                        <input type="password"
                               name="password"
                               id="passwordField"
                               class="form-input-password"
                               placeholder="Nhập mật khẩu"
                               required>
                        <div class="password-toggle" id="togglePassword">👁️</div>
                    </div>
                </div>

                <a href="forgot-password" class="forgot-link">
                    Quên mật khẩu?
                </a>

                <div class="login-actions">
                    <button type="submit" class="btn-primary">Đăng nhập</button>

                    <div class="divider">
                        <div class="divider-line"></div>
                        <span>Hoặc đăng nhập với</span>
                        <div class="divider-line"></div>
                    </div>

                    <div class="social-row">
                        <button class="social-btn">
                            <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/c/c1/Google_%22G%22_logo.svg/1200px-Google_%22G%22_logo.svg.png" alt="Google">
                        </button>

                        <button class="social-btn">
                            <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/0/05/Facebook_Logo_%282019%29.png/1024px-Facebook_Logo_%282019%29.png" alt="Facebook">
                        </button>

                        <button class="social-btn">
                            <img src="https://images.seeklogo.com/logo-png/49/2/twitter-x-logo-png_seeklogo-492396.png" alt="Twitter X">
                        </button>
                    </div>
                </div>
            </form>

            <p class="login-footer-text">
                Bạn là người mới?
                <a href="register">Đăng ký ngay.</a>
            </p>

        </div>
    </div>
</div>

<!-- Toggle password script -->
<script>
    const pw = document.getElementById("passwordField");
    const toggle = document.getElementById("togglePassword");

    toggle.addEventListener("click", () => {
        pw.type = pw.type === "password" ? "text" : "password";
    });
</script>

</body>
</html>
