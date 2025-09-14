<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cập nhật Category - Manager</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="container mt-4">

    <h2>Cập nhật Category</h2>

    <form action="${pageContext.request.contextPath}/manager/category/update"
          method="post" enctype="multipart/form-data" class="mt-3">

        <!-- ID hidden -->
        <input type="hidden" id="categoryid" name="categoryid" value="${cate.categoryId}">

        <div class="mb-3">
            <label for="categoryname" class="form-label">Tên Category:</label>
            <input type="text" id="categoryname" name="categoryname"
                   value="${cate.categoryname}" class="form-control" required>
        </div>

        <div class="mb-3">
            <label for="images" class="form-label">Ảnh hiện tại:</label><br>
            <c:if test="${not empty cate.images}">
                <c:choose>
                    <c:when test="${cate.images.substring(0,5) != 'https'}">
                        <c:url value="/image?fname=${cate.images}" var="imgUrl" />
                    </c:when>
                    <c:otherwise>
                        <c:url value="${cate.images}" var="imgUrl" />
                    </c:otherwise>
                </c:choose>
                <img src="${imgUrl}" height="120" class="mb-2"/>
            </c:if>
            <input type="file" id="images" name="images" class="form-control">
        </div>

        <div class="mb-3">
            <label for="status" class="form-label">Trạng thái:</label>
            <select id="status" name="status" class="form-select">
                <option value="1" ${cate.status == 1 ? 'selected' : ''}>Hoạt động</option>
                <option value="0" ${cate.status == 0 ? 'selected' : ''}>Không hoạt động</option>
            </select>
        </div>

        <button type="submit" class="btn btn-primary">Cập nhật</button>
        <a href="${pageContext.request.contextPath}/manager/categories" 
           class="btn btn-secondary">Hủy</a>
    </form>

</body>
</html>
