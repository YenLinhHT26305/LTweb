package Service;

import Model.User;

public interface UserService {
    User login(String username, String password);
    User findByUserName(String username);

    User findByEmail(String email);
    void updatePassword(int userId, String newPassword);
}
