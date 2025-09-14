<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Trang chủ Manager</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="container mt-4">

    <!-- Thanh điều hướng -->
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h1>Trang chủ của Manager</h1>
        <a href="<%=request.getContextPath()%>/logout" class="btn btn-danger">Đăng xuất</a>
    </div>

    <!-- Danh sách Category -->
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h3>Danh sách Category của bạn</h3>
        <a href="${pageContext.request.contextPath}/manager/category/add" class="btn btn-success">
            + Thêm Category
        </a>
    </div>

    <table class="table table-bordered table-hover">
        <thead class="table-light">
            <tr>
                <th>ID</th>
                <th>Tên Category</th>
                <th>Ảnh</th>
                <th>Trạng thái</th>
                <th>Hành động</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="c" items="${listCategory}">
                <tr>
                    <td>${c.categoryId}</td>
                    <td>${c.categoryname}</td>
                    <td>
                        <c:if test="${not empty c.images}">
                            <img src="${c.images}" alt="Category Image" width="80">
                        </c:if>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${c.status == 1}">Hoạt động</c:when>
                            <c:otherwise>Không hoạt động</c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/manager/category/edit?id=${c.categoryId}" 
                           class="btn btn-warning btn-sm">Sửa</a>
                        <a href="${pageContext.request.contextPath}/manager/category/delete?id=${c.categoryId}" 
                           class="btn btn-danger btn-sm"
                           onclick="return confirm('Bạn có chắc muốn xóa?');">Xóa</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

</body>
</html>
