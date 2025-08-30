<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng ký</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
    body {
        font-family: Arial, sans-serif;
        background: #f4f4f4;
    }
    .container {
        width: 450px;
        margin: 80px auto;
        padding: 20px;
        background: white;
        border-radius: 10px;
        box-shadow: 0px 0px 10px #ccc;
    }
    h2 {
        text-align: center;
        margin-bottom: 20px;
    }
    .alert {
        color: red;
        font-weight: bold;
        margin-bottom: 15px;
        text-align: center;
    }
    .form-group {
        margin-bottom: 15px;
    }
    .input-group {
        display: flex;
        align-items: center;
    }
    .input-group-addon {
        background: #eee;
        padding: 10px;
        border: 1px solid #ccc;
        border-right: none;
        border-radius: 5px 0 0 5px;
    }
    .form-control {
        flex: 1;
        padding: 10px;
        border: 1px solid #ccc;
        border-radius: 0 5px 5px 0;
    }
    button {
        width: 100%;
        padding: 10px;
        background: #28a745;
        border: none;
        color: white;
        font-size: 16px;
        border-radius: 5px;
        cursor: pointer;
    }
    button:hover {
        background: #218838;
    }
</style>
</head>
<body>
    <div class="container">
        <h2>Tạo tài khoản mới</h2>

        <!-- Thông báo lỗi -->
        <c:if test="${not empty alert}">
            <div class="alert">${alert}</div>
        </c:if>

        <!-- Form đăng ký -->
        <form action="${pageContext.request.contextPath}/register" method="post">
            
            <div class="form-group">
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                    <input type="text" placeholder="Tài khoản" name="username" class="form-control" required>
                </div>
            </div>

            <div class="form-group">
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                    <input type="password" placeholder="Mật khẩu" name="password" class="form-control" required>
                </div>
            </div>

            <div class="form-group">
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                    <input type="password" placeholder="Nhập lại mật khẩu" name="confirmPassword" class="form-control" required>
                </div>
            </div>

            <button type="submit">Đăng ký</button>
        </form>
    </div>
</body>
</html>
