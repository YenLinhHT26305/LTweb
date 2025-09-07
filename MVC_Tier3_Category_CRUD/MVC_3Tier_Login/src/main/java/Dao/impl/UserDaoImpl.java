package Dao.impl;

import Dao.UserDao;
import Model.User;
import DBConnection.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM [User]";
        try (Connection conn = new DBConnection().getConnectionW();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(mapUser(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public User findById(int id) {
        String sql = "SELECT * FROM [User] WHERE id=?";
        try (Connection conn = new DBConnection().getConnectionW();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapUser(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void insert(User user) {
        String sql = "INSERT INTO [User](username, email, password) VALUES(?,?,?)";
        try (Connection conn = new DBConnection().getConnectionW();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassWord());
            ps.executeUpdate();
            System.out.println("Insert thành công: " + user.getUserName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findByUserName(String username) {
        String sql = "SELECT * FROM [User] WHERE username=?";
        try (Connection conn = new DBConnection().getConnectionW();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapUser(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User findByEmail(String email) {
        String sql = "SELECT * FROM [User] WHERE email=?";
        try (Connection conn = new DBConnection().getConnectionW();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapUser(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updatePassword(int userId, String newPassword) {
        String sql = "UPDATE [User] SET password=? WHERE id=?";
        try (Connection conn = new DBConnection().getConnectionW();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, newPassword);
            ps.setInt(2, userId);
            ps.executeUpdate();
            System.out.println("Update mật khẩu thành công cho user id: " + userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private User mapUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setUserName(rs.getString("username"));
        user.setEmail(rs.getString("email"));
        user.setPassWord(rs.getString("password"));
        return user;
    }
}
