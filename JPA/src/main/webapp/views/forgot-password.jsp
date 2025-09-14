<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quên mật khẩu</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="container mt-5">

	<h2 class="mb-4">Quên mật khẩu</h2>

	<c:if test="${not empty msg}">
		<div class="alert alert-success">${msg}</div>
	</c:if>
	<c:if test="${not empty error}">
		<div class="alert alert-danger">${error}</div>
	</c:if>

	<!-- Gửi POST đến servlet /forgot-password -->
	<form method="post"
		action="${pageContext.request.contextPath}/forgot-password"
		class="card p-4 shadow-sm">
		<div class="mb-3">
			<label class="form-label">Email của bạn</label> <input type="email"
				name="email" class="form-control"
				placeholder="Nhập email đã đăng ký" required>
		</div>

		<button type="submit" class="btn btn-primary">Gửi link reset</button>

		<div class="mt-3">
			<a href="${pageContext.request.contextPath}/login"
				class="btn btn-secondary">Quay lại đăng nhập</a>
		</div>

	</form>

</body>
</html>
