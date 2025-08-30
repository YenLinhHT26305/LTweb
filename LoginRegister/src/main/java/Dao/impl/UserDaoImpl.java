package Dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DBConnection.DBConnection;
import Dao.UserDao;
import Model.User;

public class UserDaoImpl implements UserDao {

    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM [User]";
        List<User> list = new ArrayList<>();
        try (Connection conn = new DBConnection().getConnectionW();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("avatar"),
                        rs.getString("fullname"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getInt("roleid"),
                        rs.getDate("createDate")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public User findById(int id) {
        String sql = "SELECT * FROM [User] WHERE id = ?";
        try (Connection conn = new DBConnection().getConnectionW();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapUser(rs);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void insert(User user) {
        String sql = "INSERT INTO [User] (username, password, avatar, fullname, email, phone, roleid, createDate) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = new DBConnection().getConnectionW();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getUserName().trim());
            ps.setString(2, user.getPassWord().trim());
            ps.setString(3, user.getAvatar());
            ps.setString(4, user.getFullName());
            ps.setString(5, user.getEmail());
            ps.setString(6, user.getPhone());
            ps.setInt(7, user.getRoleid());
            ps.setDate(8, new java.sql.Date(user.getCreateDate().getTime()));

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findByUserName(String username) {
        String sql = "SELECT * FROM [User] WHERE LTRIM(RTRIM(username)) = ?";
        try (Connection conn = new DBConnection().getConnectionW();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username.trim());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapUser(rs);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Helper để tránh lặp code
    private User mapUser(ResultSet rs) throws Exception {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setUserName(rs.getString("username"));
        user.setPassWord(rs.getString("password"));
        user.setAvatar(rs.getString("avatar"));
        user.setFullName(rs.getString("fullname"));
        user.setEmail(rs.getString("email"));
        user.setPhone(rs.getString("phone"));
        user.setRoleid(rs.getInt("roleid"));
        user.setCreateDate(rs.getDate("createDate"));
        return user;
    }

    public static void main(String[] args) {
        try {
            UserDao userDao = new UserDaoImpl();
            User user = userDao.findByUserName("linh");
            if (user != null) {
                System.out.println("User found: " + user.getUserName() + " | Pass = " + user.getPassWord());
            } else {
                System.out.println("User not found!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
