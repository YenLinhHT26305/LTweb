package Service;

import Entity.Users;

public interface RegisterService {
	void insert(Users user);
	boolean register(String fullname, String password, String username, String
	email, String phone);
	boolean checkExistEmail(String email);
	boolean checkExistUsername(String username);

}