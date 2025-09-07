package Service.impl;

import Service.TokenService;
import DBConnection.DBConnection;

import java.sql.*;
import java.time.LocalDateTime;

public class TokenServiceImpl implements TokenService {

    @Override
    public boolean isValid(String token) {
        String sql = "SELECT expiry_time FROM PasswordResetToken WHERE token = ?";
        try (Connection conn = new DBConnection().getConnectionW();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, token);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Timestamp expiry = rs.getTimestamp("expiry_time");
                if (expiry != null) {
                    LocalDateTime expiryTime = expiry.toLocalDateTime();
                    return !expiryTime.isBefore(LocalDateTime.now());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) { 
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int getUserIdByToken(String token) {
        String sql = "SELECT user_id FROM PasswordResetToken WHERE token = ?";
        try (Connection conn = new DBConnection().getConnectionW();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, token);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("user_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public void deleteToken(String token) {
        String sql = "DELETE FROM PasswordResetToken WHERE token = ?";
        try (Connection conn = new DBConnection().getConnectionW();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, token);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveToken(int userId, String token, LocalDateTime expiry) {
        String sql = "INSERT INTO PasswordResetToken(user_id, token, expiry_time) VALUES(?,?,?)";
        try (Connection conn = new DBConnection().getConnectionW();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setString(2, token);
            ps.setTimestamp(3, Timestamp.valueOf(expiry));
            ps.executeUpdate();

            System.out.println("Token đã lưu cho userId=" + userId + ", hết hạn lúc " + expiry);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Hàm tiện ích: tạo token mặc định hết hạn sau 15 phút
    public void saveTokenWithDefaultExpiry(int userId, String token) {
        LocalDateTime expiry = LocalDateTime.now().plusMinutes(15);
        saveToken(userId, token, expiry);
    }
}
