package com.nagarro.training.service;

import java.util.List;

import com.nagarro.training.model.User;



public interface UserService {
	
public List<User> getAllUsers();
		
	

	public User getUserById(String id) ;

	public User addUser(User user) ;

	public User updateUser(User user) ;
	public void deleteUser(String id);
	public boolean isEmailAlreadyRegistered(String email);
	
	
}
