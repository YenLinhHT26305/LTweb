1. Người dùng yêu cầu reset mật khẩu
2. Người dùng nhập email trên form "Quên mật khẩu".
- Kiểm tra email có tồn tại trong hệ thống hay không.
- Nếu có, hệ thống sẽ tạo một token reset mật khẩu (thường là chuỗi ngẫu nhiên, UUID).
- Lưu token vào cơ sở dữ liệu ( PasswordResetToken ( id, user_id, token, expiry_time)).
- expiry_time để xác định thời hạn hiệu lực của token (ví dụ: 15 hoặc 30 phút).
3.  In ra link chứa token ở console (thay cho việc gửi email thật).
- Người dùng vào link này và đặt lại mật khẩu mới.
- Token sẽ bị xóa.
