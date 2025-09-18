package Service.impl;

import Dao.UserDao;
import Dao.impl.UserDaoImpl;
import Entity.Users;
import Service.UserService;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();

    @Override
    public Users login(String username, String password) {
        Users user = userDao.findByUserName(username);
        if (user != null && password.equals(user.getPassword())) {
            return user;
        }
        return null;
    }

    @Override
    public Users findByUserName(String username) {
        return userDao.findByUserName(username);
    }

    @Override
    public Users findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public void updatePassword(int userId, String newPassword) {
        userDao.updatePassword(userId, newPassword);
    }

    @Override
    public void updateProfile(int userId, String fullname, String phone, String avatarPath) {
        userDao.updateProfile(userId, fullname, phone, avatarPath);
    }
}
