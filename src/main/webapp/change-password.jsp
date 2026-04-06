<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>Đổi mật khẩu</title>

<link href="https://fonts.googleapis.com/css2?family=Plus+Jakarta+Sans:wght@400;500;700;800&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined" rel="stylesheet" />


<link rel="stylesheet" href="${pageContext.request.contextPath}/layout/change-password.css">

</head>
<body>

<jsp:include page="header.jsp"/>

<c:set var="page" value="change-password" />

<div class="container">

    <jsp:include page="sidebar-profile.jsp" />

    <div class="main">

        <h1>Đổi Mật Khẩu</h1>
        <p class="sub">Thay đổi mật khẩu tài khoản của bạn.</p>

        <c:if test="${not empty message}">
            <div class="alert-success">${message}</div>
        </c:if>

        <c:if test="${not empty error}">
            <div class="alert-error">${error}</div>
        </c:if>

        <div class="wrapper">

            <form action="change-password" method="post">

                <div class="form-group">
                    <label>Mật khẩu cũ</label>
                    <div class="input-box">
                        <input type="password" name="currentPass"
                               value="${param.currentPass}"
                               required>
                    </div>
                </div>

                <div class="form-group">
                    <label>Mật khẩu mới</label>
                    <div class="input-box">
                        <input type="password" name="newPass"
                               value="${param.newPass}"
                               required>
                    </div>
                </div>

                <div class="form-group">
                    <label>Nhập lại mật khẩu mới</label>
                    <div class="input-box">
                        <input type="password" name="confirmPass"
                               value="${param.confirmPass}"
                               required>
                    </div>
                </div>

                <div class="action-row">
                    <button type="button" class="btn-cancel"
                        onclick="window.location='edit-profile'">Hủy</button>
                    <button type="submit" class="btn-save">Đổi Mật Khẩu</button>
                </div>

            </form>

        </div>

    </div>

</div>

<jsp:include page="footer.jsp"/>

</body>
</html>
