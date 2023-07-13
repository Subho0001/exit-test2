package com.nagarro.training.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.training.model.User;
import com.nagarro.training.service.UserService;

@RestController


@RequestMapping("/user")
@CrossOrigin
public class UserController {

	@Autowired
	UserService userService;
	
	private final String KEY = "Exit_Test_Subhradip";
	
	

	@GetMapping
	public List<User> getUsers() {
		return userService.getAllUsers();
	}

	@GetMapping("/length")
	public ResponseEntity<Object> getTotlUsers(@RequestHeader("API_KEY")String API_KEY) {
		if(API_KEY.equalsIgnoreCase(KEY)) {
		return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers().size());
		}
		else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getUserById(@PathVariable String id,@RequestHeader("API_KEY")String API_KEY) {
		
		  if(API_KEY.equalsIgnoreCase(KEY)) { 
			  User user= userService.getUserById(id);
			  return ResponseEntity.status(HttpStatus.OK).body(user); } 
		  else {
			  return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		  }
	}

	@PostMapping
	public ResponseEntity<User> addUser(@RequestBody User user,@RequestHeader("API_KEY")String API_KEY) {
		if(API_KEY.equalsIgnoreCase(KEY)) {
		if (userService.isEmailAlreadyRegistered(user.getEmail())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		}
		User registeredUser = userService.addUser(user);
		return ResponseEntity.ok(registeredUser);
		}
		else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);	
		}

	}

	@PutMapping
	public ResponseEntity<User> updateUser(@RequestBody User user) {

		if (userService.isEmailAlreadyRegistered(user.getEmail())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		}

		User registeredUser = userService.updateUser(user);
		return ResponseEntity.ok(registeredUser);

	}

}
