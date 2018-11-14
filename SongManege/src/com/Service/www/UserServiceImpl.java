package com.Service.www;

import java.util.List;

import com.Dao.www.UserDao;
import com.Dao.www.UserDaoImpl;
import com.Unity.www.User;

public class UserServiceImpl implements UserService{
	
	UserDao userDao = new UserDaoImpl();
	
	public User login(String name, String password) {
		return userDao.findByNameAndPass(name, password);
	}
	@Override
	public boolean updatePassword(User user) {
		return userDao.editUser(user);
	}
	@Override
	public boolean register(User user) {
		return userDao.addUser(user);
	}
	@Override
	public boolean checkUser(String name) {
		int count = userDao.foundCountByName(name);
		System.out.println("conutservice:"+count);
		if (count == 0) {
			return false;
		} else {
			return true;
		}
	}
	@Override
	public boolean addUser(User user) {
		return userDao.addUser(user);
	}
	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}
	@Override
	public List<User> findByName(String name) {
		return userDao.findByName(name);
	}
	@Override
	public boolean deleteUser(String id) {
		return userDao.deletUser(id);
	}
	@Override
	public boolean updateUserInfo(User user) {
		return userDao.editUser(user);
	}
}
