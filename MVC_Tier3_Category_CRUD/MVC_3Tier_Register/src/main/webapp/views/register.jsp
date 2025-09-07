<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng ký tài khoản</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">

	<div class="container mt-5">
		<div class="row justify-content-center">
			<div class="col-md-5">
				<div class="card shadow-lg rounded-3">
					<div class="card-body p-4">
						<h2 class="text-center mb-4">Tạo tài khoản mới</h2>

						<!-- Hiển thị thông báo nếu có -->
						<c:if test="${alert != null}">
							<div class="alert alert-danger text-center">${alert}</div>
						</c:if>
						<c:if test="${success != null}">
							<div class="alert alert-success text-center">${success}</div>
						</c:if>

						<!-- Form đăng ký -->
						<form action="register" method="post">

							<div class="mb-3">
								<label class="form-label">Họ và tên</label> <input type="text"
									name="fullname" class="form-control"
									placeholder="Nhập họ và tên">
							</div>

							<div class="mb-3">
								<label class="form-label">Tài khoản</label> <input type="text"
									name="username" class="form-control"
									placeholder="Nhập tên tài khoản" required>
							</div>

							<div class="mb-3">
								<label class="form-label">Mật khẩu</label> <input
									type="password" name="password" class="form-control"
									placeholder="Nhập mật khẩu" required>
							</div>

							<div class="mb-3">
								<label class="form-label">Xác nhận mật khẩu</label> <input
									type="password" name="confirmPassword" class="form-control"
									placeholder="Nhập lại mật khẩu" required>
							</div>

							<div class="mb-3">
								<label class="form-label">Email</label> <input type="email"
									name="email" class="form-control" placeholder="Nhập email"
									required>
							</div>



							<button type="submit" class="btn btn-primary w-100">Đăngký</button>
						</form>

						<div class="text-center mt-3">
							<a href="http://localhost:8080/MVC_3Tier_Login/login">Đã có tài khoản? Đăng nhập</a>
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap JS -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
