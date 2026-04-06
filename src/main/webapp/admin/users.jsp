<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Quản lý Người dùng</title>

    <link href="https://fonts.googleapis.com/css2?family=Plus+Jakarta+Sans:wght@400;500;600;700&display=swap" rel="stylesheet"/>
    <link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/layout/admin.css">
</head>

<body>

<c:set var="page" value="users"/>

<div class="admin-layout">

    <jsp:include page="sidebar.jsp"/>

    <main class="admin-main">
        <div class="admin-main-inner">

            <jsp:include page="header.jsp">
                <jsp:param name="title" value="Quản lý Người dùng"/>
                <jsp:param name="subtitle" value="Thêm – chỉnh sửa – xóa tài khoản"/>
            </jsp:include>



            <!-- FORM NGƯỜI DÙNG -->
            <div class="table-card" style="margin-bottom: 24px;">
                <div class="table-wrapper">

                    <form class="user-form" method="post" action="users">

                        <input type="hidden" name="id" value="${editUser != null ? editUser.userId : ''}" />

                        <!-- USERNAME -->
                        <div class="form-row">
                            <label>Username</label>

                            <c:choose>
                                <c:when test="${editUser != null}">
                                    <input type="text" class="form-input" value="${editUser.username}" readonly />
                                    <input type="hidden" name="username" value="${editUser.username}">
                                </c:when>

                                <c:otherwise>
                                    <input type="text" name="username" class="form-input" required />
                                </c:otherwise>
                            </c:choose>
                        </div>

                        <!-- FULLNAME -->
                        <div class="form-row">
                            <label>Họ và Tên</label>
                            <input type="text" name="fullname" class="form-input"
                                   value="${editUser != null ? editUser.fullname : ''}" required>
                        </div>

                        <!-- EMAIL -->
                        <div class="form-row">
                            <label>Email</label>
                            <input type="email" name="email" class="form-input"
                                   value="${editUser != null ? editUser.email : ''}" required>
                        </div>

                        <!-- PASSWORD -->
                        <div class="form-row">
                            <label>Mật khẩu</label>
                            <input type="password" name="password" class="form-input"
                                   placeholder="${editUser != null ? 'Để trống để giữ mật khẩu cũ' : ''}">
                        </div>

                        <!-- ROLE -->
                        <div class="form-row">
                            <label>Vai trò</label>
                            <select name="role" class="form-input">
                                <option value="user"  ${editUser != null && !editUser.isAdmin ? "selected" : ""}>User</option>
                                <option value="admin" ${editUser != null &&  editUser.isAdmin ? "selected" : ""}>Admin</option>
                            </select>
                        </div>

                        <!-- STATUS -->
                        <div class="form-row">
                            <label>Trạng thái</label>
                            <select name="active" class="form-input">
                                <option value="active"   ${editUser != null && editUser.activated ? "selected" : ""}>Active</option>
                                <option value="disabled" ${editUser != null && !editUser.activated ? "selected" : ""}>Disabled</option>
                            </select>
                        </div>

                        <!-- NÚT SUBMIT -->
                        <button class="btn-primary" type="submit" name="action"
                                value="${editUser == null ? 'create' : 'update'}">
                            <span class="material-symbols-outlined">save</span>
                            ${editUser == null ? "Thêm người dùng" : "Cập nhật"}
                        </button>


            
                    </form>

                </div>
            </div>

            <!-- DANH SÁCH -->
            <div class="table-card">
                <h2 class="table-title">Danh sách người dùng</h2>

                <div class="table-wrapper">
                    <table class="admin-table">

                        <thead>
                        <tr>
                            <th>Người dùng</th>
                            <th>Vai trò</th>
                            <th>Email</th>
                            <th>Trạng thái</th>
                            <th>Hành động</th>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach var="u" items="${list}">
                            <tr>
                                <td>
                                    <div class="user-cell">
                                        <div class="user-avatar"
                                             style="background-image:url('https://i.pravatar.cc/150?u=${u.email}');"></div>

                                        <div class="user-info">
                                            <div class="user-name">${u.fullname}</div>
                                            <div class="user-email">${u.email}</div>
                                        </div>
                                    </div>
                                </td>

                                <td>
                                    <span class="badge ${u.isAdmin ? 'badge-green' : 'badge-yellow'}">
                                        ${u.isAdmin ? "Admin" : "User"}
                                    </span>
                                </td>

                                <td>${u.email}</td>

                                <td>
                                    <span class="badge ${u.activated ? 'badge-green' : 'badge-red'}">
                                        ${u.activated ? "Active" : "Disabled"}
                                    </span>
                                </td>

                                <td>
                                    <div class="action-buttons">
                                        <a class="icon-btn" href="users?action=edit&id=${u.userId}">
                                            <span class="material-symbols-outlined">edit</span>
                                        </a>

                                        <a class="icon-btn delete"
                                           href="users?action=delete&id=${u.userId}"
                                           onclick="return confirm('Bạn chắc muốn xóa người dùng này?');">
                                            <span class="material-symbols-outlined">delete</span>
                                        </a>
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

</body>
</html>
