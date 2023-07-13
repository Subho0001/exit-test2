package com.nagarro.training.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.nagarro.training.model.User;
import com.nagarro.training.repository.UserRepository;
import com.nagarro.training.service.UserService;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}


	@Override
	public User getUserById(String id) {
		Optional<User> optional=userRepository.findById(id);
		return optional.isPresent()?optional.get():null;
	}


	@Override
	public User addUser(User user) {
		return userRepository.save(user);
	}


	@Override
	public User updateUser(User user) {
		return userRepository.save(user);
	}


	@Override
	public void deleteUser(String id) {
		Optional<User> optional=userRepository.findById(id);
		if(optional.isPresent()) {
			userRepository.delete(optional.get());
		}
		
	}
	public boolean isEmailAlreadyRegistered(String email) {
	    return userRepository.existsById(email);
	  }


	
	
	
	

	
}
