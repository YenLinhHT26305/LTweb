package Service.impl;

import java.time.LocalDateTime;

import Dao.RegisterDao;
import Dao.impl.RegisterDaoImpl;
import Entity.Users;
import Service.RegisterService;

public class RegisterServiceImpl implements RegisterService {
	RegisterDao reDao = new RegisterDaoImpl();

	@Override
	public boolean register(String username, String password, String email, String fullname, String phone) {
		// Kiểm tra trùng email
		if (reDao.checkExistEmail(email)) {
			return false;
		}
		// Kiểm tra trùng username
		if (reDao.checkExistUsername(username)) {
			return false;
		}
		// Kiểm tra trùng phone
		if (reDao.checkExistPhone(phone)) {
			return false;
		}
		Users user = new Users();
		user.setEmail(email);
		user.setUsername(username);
		user.setFullname(fullname);
		user.setPassword(password);
		user.setAvatar(null);
		user.setRoleId(5);
		user.setPhone(phone);

		// Set ngày giờ hiện tại
		user.setCreateDate(LocalDateTime.now());
		reDao.insert(user);
		return true;
	}

	@Override
	public boolean checkExistEmail(String email) {
		return reDao.checkExistEmail(email);
	}

	@Override
	public boolean checkExistUsername(String username) {
		return reDao.checkExistUsername(username);
	}

	@Override
	public void insert(Users user) {
		reDao.insert(user);
	}
}
