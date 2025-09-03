package Dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import DBConnection.DBConnection;
import Dao.UserDao;
import Model.User;

public class UserDaoImpl implements UserDao {

	@Override
	public void insert(User user) {
	    String sql = "INSERT INTO [User](username, password, avatar, fullname, email, phone, roleid, createDate) "
	               + "VALUES (?,?,?,?,?,?,?,?)";
	    try (Connection conn = new DBConnection().getConnectionW();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setString(1, user.getUserName());
	        ps.setString(2, user.getPassWord());
	        ps.setString(3, user.getAvatar());
	        ps.setString(4, user.getFullName());
	        ps.setString(5, user.getEmail());
	        ps.setString(6, user.getPhone());
	        ps.setInt(7, user.getRoleid());

	        // Nếu user.getCreateDate() != null thì dùng nó, còn không thì dùng ngày hiện tại
	        if (user.getCreateDate() != null) {
	            ps.setDate(8, new java.sql.Date(user.getCreateDate().getTime()));
	        } else {
	            ps.setDate(8, new java.sql.Date(System.currentTimeMillis()));
	        }

	        ps.executeUpdate();
	        System.out.println("✅ Insert user thành công: " + user.getUserName());
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

    @Override
    public boolean checkExistEmail(String email) {
        String query = "SELECT 1 FROM [User] WHERE email = ?";
        try (Connection conn = new DBConnection().getConnectionW();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean checkExistUsername(String username) {
        String query = "SELECT 1 FROM [User] WHERE username = ?";
        try (Connection conn = new DBConnection().getConnectionW();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean checkExistPhone(String phone) {
        String query = "SELECT 1 FROM [User] WHERE phone = ?";
        try (Connection conn = new DBConnection().getConnectionW();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setString(1, phone);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
