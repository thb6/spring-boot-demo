package com.sample.talha.service;

import java.util.List;

import com.sample.talha.entities.Users;


public interface UserService {

	public List<Users> getUsers();

	public void save(Users user);

	public Users getUserById(int id);
	
	public Users getUserByEmail(String email);

	public void deleteUserById(int id);
	
	public void updateUserByEmail(Users updatedUser);
}
