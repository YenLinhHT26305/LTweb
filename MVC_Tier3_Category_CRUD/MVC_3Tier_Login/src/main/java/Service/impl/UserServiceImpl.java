package Service.impl;

import Dao.UserDao;
import Dao.impl.UserDaoImpl;
import Model.User;
import Service.UserService;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();

    @Override
    public User login(String username, String password) {
        User user = userDao.findByUserName(username);
        if (user != null && password.equals(user.getPassWord())) {
            return user;
        }
        return null;
    }

    @Override
    public User findByUserName(String username) {
        return userDao.findByUserName(username);
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public void updatePassword(int userId, String newPassword) {
        userDao.updatePassword(userId, newPassword);
    }
}
