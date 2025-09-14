<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Trang đăng nhập</title>
    <link rel="stylesheet" href="https://dnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="container mt-5">

    <h2 class="mb-4">Đăng nhập</h2>

    <c:if test="${not empty alert}">
        <h3 class="alert alert-danger">${alert}</h3>
    </c:if>
    <!-- gửi POST đến servlet /register -->
    <form action="${pageContext.request.contextPath}/login" method="post">
        <div class="mb-3">
            <label class="form-label">Tài khoản</label>
            <input type="text" name="username" class="form-control" placeholder="Nhập tài khoản" required>
        </div>

        <div class="mb-3">
            <label class="form-label">Mật khẩu</label>
            <input type="password" name="password" class="form-control" placeholder="Nhập mật khẩu" required>
        </div>

        <button type="submit" class="btn btn-primary">Đăng nhập</button>
        <!-- quay về trang login qua servlet /login -->
        <a href="http://localhost:8080/JPA/register" class="btn btn-secondary ms-2">Đăng kí</a>
    </form>

    <div class="mt-3">
        <!-- forgot password cũng nên mapping qua servlet -->
        <a href="${pageContext.request.contextPath}/forgot-password">Quên mật khẩu?</a>
    </div>

</body>
</html>
