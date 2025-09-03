package Service.impl;

import Dao.UserDao;
import Dao.impl.UserDaoImpl;
import Model.User;
import Service.UserService;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();

    @Override
    public boolean register(String username, String password, String email, String fullname, String phone) {
        // Kiểm tra trùng email
        if (userDao.checkExistEmail(email)) {
            return false;
        }
        // Kiểm tra trùng username
        if (userDao.checkExistUsername(username)) {
            return false;
        }
        // Kiểm tra trùng phone
        if (userDao.checkExistPhone(phone)) {
            return false;
        }

        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);

        User user = new User();
        user.setEmail(email);
        user.setUserName(username);
        user.setFullName(fullname);
        user.setPassWord(password);
        user.setAvatar(null);
        user.setRoleid(5);
        user.setPhone(phone);
        user.setCreateDate(date);

        userDao.insert(user);

        return true;
    }

    @Override
    public boolean checkExistEmail(String email) {
        return userDao.checkExistEmail(email);
    }

    @Override
    public boolean checkExistUsername(String username) {
        return userDao.checkExistUsername(username);
    }

    @Override
    public void insert(User user) {
        userDao.insert(user);
    }
}
