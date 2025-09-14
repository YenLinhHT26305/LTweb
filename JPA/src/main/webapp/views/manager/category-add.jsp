<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thêm Category - Manager</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="container mt-4">

    <h2>Thêm Category mới</h2>

    <!-- Form thêm Category -->
    <form action="${pageContext.request.contextPath}/manager/category/insert" 
          method="post" enctype="multipart/form-data" class="mt-3">

        <div class="mb-3">
            <label for="categoryname" class="form-label">Tên Category:</label>
            <input type="text" id="categoryname" name="categoryname" 
                   class="form-control" required>
        </div>

        <div class="mb-3">
            <label for="images" class="form-label">Ảnh:</label>
            <input type="file" id="images" name="images" class="form-control">
        </div>

        <div class="mb-3">
            <label for="status" class="form-label">Trạng thái:</label>
            <select id="status" name="status" class="form-select">
                <option value="1">Hoạt động</option>
                <option value="0">Không hoạt động</option>
            </select>
        </div>

        <button type="submit" class="btn btn-primary">Lưu</button>
        <a href="${pageContext.request.contextPath}/manager/categories" 
           class="btn btn-secondary">Hủy</a>
    </form>

</body>
</html>
