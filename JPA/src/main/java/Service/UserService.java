package Service;

import Entity.Users;

public interface UserService {
    Users login(String username, String password);
    
    Users findByUserName(String username);
    
    Users findByEmail(String email);
    
    void updatePassword(int userId, String newPassword);
    
    void updateProfile(int userId, String fullname, String phone, String avatarPath);
}
