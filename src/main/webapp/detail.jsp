<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${video.title}</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/layout/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/layout/detail.css">

</head>
<body>

<jsp:include page="header.jsp"/>

<div class="container">

    <!-- VIDEO CHÍNH -->
    <div class="video-area">

        <div class="video-box">
            <iframe width="100%" height="100%"
                src="https://www.youtube.com/embed/${video.youtubeId}"
                title="${video.title}" allowfullscreen>
            </iframe>
        </div>

        <div class="title">${video.title}</div>

        <p class="description">${video.description}</p>

        <!-- ACTION BUTTONS -->
        <div class="action-buttons">

            <!-- LIKE / UNLIKE -->
            <c:choose>
                <c:when test="${isFavorite}">

                    <a href="favorite?action=unlike&videoId=${video.id}&from=detail" class="btn">
					    ❌ Bỏ thích
					</a>
                    
                </c:when>

                <c:otherwise>
                    <a href="favorite?action=like&videoId=${video.id}" class="btn">
                        👍 Thích
                    </a>
                </c:otherwise>
            </c:choose>

            <!-- SHARE -->
            <a href="share?id=${video.id}" class="btn">📤 Chia sẻ</a>

        </div>

    </div>

    <!-- SIDEBAR ĐỀ XUẤT -->
    <div class="sidebar">
        <h2>Đề xuất cho bạn</h2>

        <c:forEach var="v" items="${suggest}">
            <a href="detail?id=${v.id}" style="text-decoration:none; color:white;">
            
                <div class="suggest-item">
                    <div class="suggest-thumb"
                         style="background-image:url('${v.poster}');"></div>

                    <div>
                        <div class="suggest-title">${v.title}</div>
                        <div class="suggest-meta">${v.views} lượt xem</div>
                    </div>
                </div>
                
            </a>
        </c:forEach>
    </div>

</div>

<jsp:include page="footer.jsp"/>

</body>
</html>
