package Service;
import Model.User;

public interface UserService {
	void insert(User user);
	boolean register(String fullname, String password, String username, String
	email, String phone);
	boolean checkExistEmail(String email);
	boolean checkExistUsername(String username);

}