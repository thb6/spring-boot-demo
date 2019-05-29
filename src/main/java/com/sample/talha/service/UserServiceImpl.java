package com.sample.talha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sample.talha.dao.UserDao;
import com.sample.talha.entities.Users;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	
	@Override
	@Transactional
	public List<Users> getUsers() {
		return userDao.getUsers();
	}

	@Override
	@Transactional
	public void save(Users user) {
		userDao.save(user);
	}

	@Override
	@Transactional
	public Users getUserById(int id) {
		return userDao.getUserById(id);
	}

	@Override
	@Transactional
	public void deleteUserById(int id) {
		userDao.deleteUserById(id);
	}

	@Override
	public Users getUserByEmail(String email) {
		return userDao.getUserByEmail(email);
	}

	@Override
	public void updateUserByEmail(Users updatedUser) {
		 userDao.updateUserByEmail(updatedUser);
	}

}
