<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>

    <!-- Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Plus+Jakarta+Sans:wght@400;500;600;700&display=swap" rel="stylesheet"/>
    <link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined" rel="stylesheet"/>

    <!-- Custom Admin CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/layout/admin.css">

<link rel="stylesheet" href="https://cdn.datatables.net/1.13.6/css/dataTables.bootstrap5.min.css">

<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.13.6/js/dataTables.bootstrap5.min.js"></script>

</head>
<body>

<c:set var="page" value="dashboard"/>

<div class="admin-layout">

    <jsp:include page="sidebar.jsp"/>

    <main class="admin-main">
        <div class="admin-main-inner">

            <jsp:include page="header.jsp">
                <jsp:param name="title" value="Bảng điều khiển"/>
                <jsp:param name="subtitle" value="Tổng quan hoạt động của hệ thống"/>
            </jsp:include>

            <!-- STAT CARDS -->
            <div class="stats-grid">
                <div class="stat-card">
                    <p class="stat-title">Tổng số phim</p>
                    <p class="stat-value">${totalVideos}</p>
                </div>
                <div class="stat-card">
                    <p class="stat-title">Tổng số người dùng</p>
                    <p class="stat-value">${totalUsers}</p>
                </div>
                <div class="stat-card">
                    <p class="stat-title">Tổng lượt xem</p>
                    <p class="stat-value">${totalViews}</p>
                </div>
                <div class="stat-card">
                    <p class="stat-title">Tổng lượt thích</p>
                    <p class="stat-value">${totalFavorites}</p>
                </div>
            </div>

            <!-- TABLE -->
            <div class="table-card"> 
                <h2 class="table-title">Phim mới được tải lên</h2>

                <div class="table-wrapper">
                    <table id="videoTable" class="table table-hover table-striped admin-table">
                    
                        <thead>
                            <tr>
                                <th>Tên phim</th>
                                <th>Mô tả</th>
                                <th>Lượt xem</th>
                                <th>Trạng thái</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="v" items="${latestVideos}">
                                <tr>
                                    <td>${v.title}</td>
                                    <td style="max-width:360px; overflow:hidden; text-overflow:ellipsis; white-space:nowrap;">
                                        ${v.description}
                                    </td>
                                    <td>${v.views}</td>
                                    <td>
                                        <span class="badge ${v.active ? 'badge-green' : 'badge-yellow'}">
                                            <c:choose>
                                                <c:when test="${v.active}">Đã xuất bản</c:when>
                                                <c:otherwise>Nháp</c:otherwise>
                                            </c:choose>
                                        </span>
                                    </td>
                                </tr>
                            </c:forEach>

                            <c:if test="${empty latestVideos}">
                                <tr>
                                    <td colspan="4" style="text-align:center; padding:14px;">
                                        Chưa có phim nào.
                                    </td>
                                </tr>
                            </c:if>
                        </tbody>
                    </table>
                </div>
            </div>

            <jsp:include page="footer.jsp"/>

        </div>
    </main>

</div>

<script>
$(document).ready(function () {
    $('#videoTable').DataTable({
        "pageLength": 5,
        "lengthMenu": [5, 10, 20, 50, 100],
        "pagingType": "simple_numbers",
        "language": {
            "lengthMenu": "Hiển thị _MENU_ video",
            "info": "Trang _PAGE_ / _PAGES_",
            "search": "",
            "searchPlaceholder": "Tìm kiếm...",
            "paginate": {
                "first": "«",
                "last": "»",
                "next": "›",
                "previous": "‹"
            }
        }
    });
});
</script>


</body>
</html>
