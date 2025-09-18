BÀI TẬP 04

Thực hiện: Viết bằng JPA và commit code lên github

Thực hiện trên database có 02 bản: Users và Category, Một user có nhiều category.
Thực hiện đăng nhập với roleid tương ứng (1-user, 2-manager, 3- admin), nếu đăng nhập thành công thì chuyển hướng về URL theo role tương ứng như sau (user: /user/home ; manager: /manager/home; admin: /admin/home)
Dùng Filter để lọc URL tương ứng với RoleID.
Trang home của role user và admin sẽ hiển thị danh sách tất cả category, trang home của role manager sẽ hiển thị danh sách các category của userid tương ứng.
Thực hiện thêm, sửa, xem, xóa category của chính mình tạo ra theo role.

BÀI TẬP 05 
Thực hiện:

Thự hiện cấu hình Sitemesh Decorator 3 với Template Bootstrap. (user (pass: 123)
Làm chức năng profile để update: fullname, phone, images (có sử dụng upload file bằng multipart) (admin (pass: 123)
