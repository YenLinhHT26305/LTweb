<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đặt lại mật khẩu</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="container mt-5">

	<h2 class="mb-4">Đặt lại mật khẩu</h2>

	<c:if test="${not empty msg}">
		<div class="alert alert-success">${msg}</div>
	</c:if>
	<c:if test="${not empty error}">
		<div class="alert alert-danger">${error}</div>
	</c:if>

	<!-- Gửi POST đến servlet /reset-password -->
	<form method="post"
		action="${pageContext.request.contextPath}/reset-password"
		class="card p-4 shadow-sm">
		<!-- Token ẩn được servlet forward sang -->
		<input type="hidden" name="token" value="${token}" />

		<div class="mb-3">
			<label class="form-label">Mật khẩu mới</label> <input type="password"
				name="password" class="form-control" placeholder="Nhập mật khẩu mới"
				required>
		</div>

		<button type="submit" class="btn btn-primary">Đổi mật khẩu</button>

		<div class="mt-3">
			<a href="${pageContext.request.contextPath}/login"
				class="btn btn-secondary ms-2">Quay lại đăng nhập</a>

		</div>

	</form>

</body>
</html>
