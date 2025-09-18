<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thông tin cá nhân</title>
<style>
body {
	font-family: Arial, sans-serif;
	background: #f4f4f9;
	margin: 0;
	padding: 0;
}

.container {
	width: 500px;
	margin: 40px auto;
	background: #fff;
	padding: 20px 30px;
	border-radius: 10px;
	box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
}

h2 {
	text-align: center;
	color: #333;
}

.form-group {
	margin-bottom: 15px;
}

label {
	display: block;
	margin-bottom: 6px;
	color: #555;
	font-weight: bold;
}

input[type="text"], input[type="file"] {
	width: 100%;
	padding: 10px;
	border: 1px solid #ccc;
	border-radius: 6px;
	box-sizing: border-box;
}

.avatar-preview {
	text-align: center;
	margin: 15px 0;
}

.avatar-preview img {
	width: 120px;        /* cố định chiều ngang */
	height: 120px;       /* cố định chiều dọc */
	border-radius: 50%;  /* bo tròn */
	border: 2px solid #ddd;
	object-fit: cover;   /* cắt ảnh để lấp đầy khung */
}

button {
	width: 100%;
	padding: 12px;
	background: #28a745;
	color: #fff;
	border: none;
	border-radius: 6px;
	font-size: 16px;
	cursor: pointer;
}

button:hover {
	background: #218838;
}

.success {
	text-align: center;
	color: green;
	margin-bottom: 10px;
}
</style>
</head>
<body>
	<div class="container">
		<h2>Cập nhật thông tin</h2>

		<%
		if (request.getParameter("success") != null) {
		%>
		<p class="success">Cập nhật thành công!</p>
		<%
		}
		%>

		<form action="${pageContext.request.contextPath}/profile"
			method="post" enctype="multipart/form-data">
			<input type="hidden" name="userId" value="${sessionScope.account.id}" />

			<div class="form-group">
				<label for="fullname">Họ và tên</label> 
				<input type="text" id="fullname" name="fullname"
					value="${sessionScope.account.fullname}" required>
			</div>

			<div class="form-group">
				<label for="phone">Số điện thoại</label> 
				<input type="text" id="phone" name="phone"
					value="${sessionScope.account.phone}" required>
			</div>

			<div class="form-group">
				<label for="avatar">Ảnh đại diện</label> 
				<input type="file" id="avatar" name="avatar" accept="image/*"
					onchange="previewImage(event)">
			</div>

			<div class="avatar-preview">
				<img id="preview"
					src="<%=(session.getAttribute("account") != null
		&& ((Entity.Users) session.getAttribute("account")).getAvatar() != null)
				? request.getContextPath() + "/" + ((Entity.Users) session.getAttribute("account")).getAvatar()
				: request.getContextPath() + "/uploads/default.png"%>"
					alt="Avatar">
			</div>

			<button type="submit">Lưu thay đổi</button>
		</form>
	</div>

	<script>
		function previewImage(event) {
			const preview = document.getElementById("preview");
			preview.src = URL.createObjectURL(event.target.files[0]);
		}
	</script>
</body>
</html>
