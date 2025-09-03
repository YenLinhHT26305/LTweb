package Service;
import Model.User;

public interface UserService {
    User login(String username, String password);
    User FindByUserName(String username);
}