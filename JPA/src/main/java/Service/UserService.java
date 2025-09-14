package Service;

import Entity.Users;

public interface UserService {
    Users login(String username, String password);
    Users findByUserName(String username);
    Users findByEmail(String email);
    void updatePassword(int userId, String newPassword);
    
}
