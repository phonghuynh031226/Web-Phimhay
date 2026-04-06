<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Báo cáo thống kê</title>

    <link href="https://fonts.googleapis.com/css2?family=Plus+Jakarta+Sans:wght@400;500;600;700&display=swap" rel="stylesheet"/>
    <link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined" rel="stylesheet"/>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/layout/admin.css">
</head>

<body>

<c:set var="page" value="report" />

<div class="admin-layout">

    <jsp:include page="sidebar.jsp"/>

    <main class="admin-main">
        <div class="admin-main-inner">

            <jsp:include page="header.jsp">
                <jsp:param name="title" value="Báo cáo thống kê"/>
                <jsp:param name="subtitle" value="Xem tổng hợp hoặc chi tiết lượt yêu thích"/>
            </jsp:include>

            <!-- TAB MENU -->
            <div class="report-tabs">
                <a href="reports?type=summary"
                   class="report-tab ${param.type == 'summary' || empty param.type ? 'active' : ''}">
                    <span class="material-symbols-outlined">favorite</span>
                    Thống kê yêu thích
                </a>

                <a href="reports?type=detail"
                   class="report-tab ${param.type == 'detail' ? 'active' : ''}">
                    <span class="material-symbols-outlined">group</span>
                    Người dùng yêu thích
                </a>
            </div>

            <!-- ============================= -->
            <!-- TAB 1: SUMMARY REPORT -->
            <!-- ============================= -->
            <c:if test="${param.type == 'summary' || empty param.type}">

                <h2 class="section-title">Tổng hợp lượt thích theo video</h2>

                <div class="table-card">
                    <div class="table-wrapper">
                        <table class="admin-table">
                            <thead>
                                <tr>
                                    <th>Video</th>
                                    <th>Lượt thích</th>
                                    <th>Lần thích cuối</th>
                                </tr>
                            </thead>

                            <tbody>
                                <c:forEach var="r" items="${summary}">
                                    <tr>
                                        <!-- r[0] = video title -->
                                        <td>${r[0]}</td>

                                        <!-- r[1] = like count -->
                                        <td><span class="badge badge-blue">${r[1]}</span></td>

                                        <!-- Không có newestLike trong DAO -->
                                        <td>—</td>
                                    </tr>
                                </c:forEach>
                            </tbody>

                        </table>
                    </div>
                </div>

            </c:if>

            <!-- ============================= -->
            <!-- TAB 2: DETAIL REPORT -->
            <!-- ============================= -->
            <c:if test="${param.type == 'detail'}">

                <h2 class="section-title">Danh sách người dùng đã yêu thích</h2>

                <form method="get" action="reports" class="filter-form">
                    <input type="hidden" name="type" value="detail"/>

                    <label class="filter-label">Chọn video:</label>

                    <select name="videoId" class="filter-select" onchange="this.form.submit()">
                        <c:forEach var="v" items="${videos}">
                            <option value="${v.id}"
                                ${v.id == param.videoId ? 'selected' : ''}>
                                ${v.title}
                            </option>
                        </c:forEach>
                    </select>
                </form>

                <div class="table-card">
                    <div class="table-wrapper">
                        <table class="admin-table">
                            <thead>
                                <tr>
                                    <th>Người dùng</th>
                                    <th>Email</th>
                                    <th>Ngày thích</th>
                                </tr>
                            </thead>

                            <tbody>
                                <c:forEach var="f" items="${detail}">
                                    <tr>
                                        <td>${f.user.fullname}</td>
                                        <td>${f.user.email}</td>
                                        <td>${f.likedDate}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>

                        </table>
                    </div>
                </div>

            </c:if>

            <jsp:include page="footer.jsp"/>

        </div>
    </main>

</div>

</body>
</html>
