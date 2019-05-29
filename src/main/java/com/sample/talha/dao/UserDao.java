package com.sample.talha.dao;

import java.util.List;

import com.sample.talha.entities.Users;

public interface UserDao {

	public List<Users> getUsers();

	public void save(Users user);

	public Users getUserById(int id);

	public void deleteUserById(int id);

	public Users getUserByEmail(String email);

	public void updateUserByEmail(Users updatedUser);
}
