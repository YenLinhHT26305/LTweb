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
        <a href="<%=request.getContextPath()%>/logout" class="logout-btn">Đăng xuất</a>
    </div>

    <p>Xin chào, bạn đang đăng nhập với vai trò <strong>Manager</strong>.</p>

</body>
</html>
