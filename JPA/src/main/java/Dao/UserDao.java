package Dao;

import java.util.List;

import Entity.Users;

public interface UserDao {
	List<Users> findAll();

	Users findById(int id);

	void insert(Users user);

	Users findByUserName(String username);

	Users findByEmail(String email);

	void update(Users user);
	 
	void updatePassword(int userId, String newPassword);
	
    void updateProfile(int userId, String fullname, String phone, String avatarPath);

}
