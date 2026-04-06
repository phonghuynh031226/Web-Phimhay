<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Quản lý Video</title>

    <link href="https://fonts.googleapis.com/css2?family=Plus+Jakarta+Sans:wght@400;500;600;700&display=swap" rel="stylesheet"/>
    <link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/layout/admin.css">

    <!-- SWEETALERT2 -->
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>

<body>

<c:set var="page" value="videos"/>

<div class="admin-layout">

    <jsp:include page="sidebar.jsp" />

    <main class="admin-main">
        <div class="admin-main-inner">

            <jsp:include page="header.jsp">
                <jsp:param name="title" value="Quản lý Video"/>
                <jsp:param name="subtitle" value="Thêm, chỉnh sửa và xóa video"/>
            </jsp:include>


            <!-- FORM VIDEO -->
            <div class="table-card" style="margin-bottom:24px;">
                <div class="table-wrapper">

                    <form class="admin-form" method="post" action="videos">

                        <input type="hidden" name="id" value="${video.id}" />

                        <div class="form-row">
                            <label>Tiêu đề Video</label>
                            <input type="text" name="title" class="form-input"
                                   value="${video.title}" required>
                        </div>

                        <div class="form-row">
                            <label>Link Thumbnail</label>
                            <input type="text" name="poster" class="form-input"
                                   value="${video.poster}" required>
                        </div>

                        <div class="form-row">
                            <label>Youtube ID</label>
                            <input type="text" name="youtubeId" class="form-input"
                                   value="${video.youtubeId}" required>
                        </div>

                        <div class="form-row">
                            <label>Mô tả</label>
                            <textarea name="description" class="form-input"
                                      rows="3">${video.description}</textarea>
                        </div>

                        <div class="form-row" style="margin-top:10px;">
                            <button class="btn-primary" type="submit" name="action"
                                    value="${video == null ? 'create' : 'update'}">
                                <span class="material-symbols-outlined">save</span>
                                ${video == null ? "Thêm Video" : "Cập nhật Video"}
                            </button>

                            <c:if test="${video != null}">
                                <a href="videos" class="btn-secondary">
                                    <span class="material-symbols-outlined">close</span>
                                    Hủy chỉnh sửa
                                </a>
                            </c:if>
                        </div>

                    </form>

                </div>
            </div>


            <!-- VIDEO LIST -->
            <div class="table-card">
                <h2 class="table-title">Danh sách Video</h2>

                <div class="table-wrapper">
                    <table class="admin-table">

                        <thead>
                        <tr>
                            <th>Poster</th>
                            <th>Tiêu đề</th>
                            <th>Mô tả</th>
                            <th>Lượt xem</th>
                            <th>Trạng thái</th>
                            <th>Hành động</th>
                        </tr>
                        </thead>

                        <tbody>

                        <c:forEach var="v" items="${list}">
                            <tr>

                                <td><img src="${v.poster}" class="thumb-img"></td>

                                <td>${v.title}</td>

                                <td style="max-width:320px; white-space:nowrap; overflow:hidden; text-overflow:ellipsis;">
                                    ${v.description}
                                </td>

                                <td>${v.views}</td>

                                <td>
                                    <span class="badge ${v.active ? 'badge-green' : 'badge-red'}">
                                        ${v.active ? "Công khai" : "Ẩn"}
                                    </span>
                                </td>

                                <td>
                                    <div class="action-buttons">

                                        <!-- EDIT -->
                                        <a class="icon-btn" href="videos?action=edit&id=${v.id}">
                                            <span class="material-symbols-outlined">edit</span>
                                        </a>

                                        <!-- DELETE BUTTON -->
                                        <button type="button" class="icon-btn delete"
                                                onclick="confirmDelete(${v.id})">
                                            <span class="material-symbols-outlined">delete</span>
                                        </button>

                                    </div>
                                </td>

                            </tr>
                        </c:forEach>

                        </tbody>

                    </table>
                </div>
            </div>

            <jsp:include page="footer.jsp"/>

        </div>
    </main>

</div>


<!-- FORM POST HIDDEN FOR DELETE -->
<form id="deleteForm" method="post" action="videos" style="display:none;">
    <input type="hidden" name="id" id="deleteId">
    <input type="hidden" name="action" value="delete">
</form>


<!-- SWEETALERT DELETE SCRIPT -->
<script>
function confirmDelete(id) {
    Swal.fire({
        title: "Xóa video?",
        text: "Video và tất cả lượt yêu thích liên quan sẽ bị xóa!",
        icon: "warning",
        showCancelButton: true,
        confirmButtonText: "Xóa ngay",
        cancelButtonText: "Hủy",
        confirmButtonColor: "#d33",
        cancelButtonColor: "#3085d6",
        reverseButtons: true
    }).then((result) => {
        if (result.isConfirmed) {
            // gọi GET /admin/videos?action=delete&id=...
            window.location.href = "videos?action=delete&id=" + id;
        }
    });
}

</script>

</body>
</html>
