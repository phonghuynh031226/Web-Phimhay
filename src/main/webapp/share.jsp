<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chia sẻ phim</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/layout/share.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

</head>
<body>

<jsp:include page="header.jsp" />

<main>
    <div class="share-wrapper">
        <div class="share-card">

            <h1 class="share-title">Chia sẻ phim qua Email</h1>
            <p class="share-subtitle">Gửi bộ phim này cho bạn bè của bạn.</p>

            <!-- THÔNG BÁO -->
            <c:if test="${not empty message}">
                <div class="alert-success">${message}</div>
            </c:if>
            <c:if test="${not empty error}">
                <div class="alert-error">${error}</div>
            </c:if>

            <!-- MOVIE PREVIEW -->
            <c:if test="${not empty video}">
                <div class="movie-preview">
                    <div class="movie-thumb"
                         style="background-image:url('${video.poster}');"></div>

                    <div>
                        <h3>${video.title}</h3>
                        <p>Lượt xem: ${video.views}</p>
                    </div>
                </div>
            </c:if>

            <form action="share" method="post">

                <input type="hidden" name="videoId" value="${video.id}" />

                <label for="recipient-email">Email người nhận</label>
                <div class="input-wrapper">
                    <span>✉</span>
                    <input id="recipient-email"
                           name="email"
                           type="email"
                           class="share-input"
                           placeholder="Nhập địa chỉ email"
                           value="${email}"
                           required>
                </div>

                <button type="submit" class="share-btn">
                    <span>📨</span>
                    <span>Gửi</span>
                </button>

                <a href="detail?id=${video.id}" class="back-btn">← Quay lại</a>

            </form>

        </div>
    </div>
</main>

<jsp:include page="footer.jsp" />

</body>
</html>
