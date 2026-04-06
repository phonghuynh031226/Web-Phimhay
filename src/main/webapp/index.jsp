<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang chủ</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/layout/style.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

</head>

<body>
<jsp:include page="header.jsp" />

<div class="container">
<!-- ============== DANH SÁCH VIDEO ============== -->
<section>
    <h2>Phim Thịnh Hành</h2>

    <c:if test="${empty videos}">
        <p style="color:white; padding:20px;">Chưa có video nào!</p>
    </c:if>

    <div class="grid">
        <c:forEach var="v" items="${videos}">
            <a class="movie-card" href="detail?id=${v.id}">
                <div class="movie-thumb" 
                     style="background-image:url('${v.poster}');"></div>

                <div class="movie-info">
                    <h3>${v.title}</h3>
                    <p>Lượt xem: ${v.views}</p>
                </div>
            </a>
        </c:forEach>
    </div>
</section>

<!-- ============== MỚI CẬP NHẬT (DÙNG LẠI videos CHO ĐẸP LAYOUT) ============== -->
<section>
    <h2>Mới Cập Nhật</h2>

    <div class="grid">
        <c:forEach var="v" items="${videos}">
            <a class="movie-card" href="detail?id=${v.id}">
                <div class="movie-thumb"
                     style="background-image:url('${v.poster}');"></div>

                <div class="movie-info">
                    <h3>${v.title}</h3>
                    <p>Lượt xem: ${v.views}</p>
                </div>
            </a>
        </c:forEach>
    </div>
</section>


<!-- ============== PHÂN TRANG ============== -->
<c:if test="${not empty totalPage}">
<div class="pagination">
    <ul class="pagination">

        <li class="page-item">
            <a class="page-link" href="index?page=${page-1 > 1 ? page-1 : 1}">
                &laquo;
            </a>
        </li>

        <c:forEach begin="1" end="${totalPage}" var="p">
            <li class="page-item">
                <a class="page-link ${p == page ? 'active' : ''}" 
                   href="index?page=${p}">
                   ${p}
                </a>
            </li>
        </c:forEach>

        <li class="page-item">
            <a class="page-link" href="index?page=${page+1 < totalPage ? page+1 : totalPage}">
                &raquo;
            </a>
        </li>

    </ul>
</div>
</c:if>

</div>
<jsp:include page="footer.jsp" />

</body>
</html>
