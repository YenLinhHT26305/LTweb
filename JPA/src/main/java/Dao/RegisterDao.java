package Dao;

import Entity.Users;

public interface RegisterDao {
	void insert(Users user);
	boolean checkExistEmail(String email);
	boolean checkExistUsername(String username);
	boolean checkExistPhone(String phone);
}
