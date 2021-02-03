package com.revature.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.service.models.Project;
import com.revature.service.models.User;
import com.revature.service.services.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	@PostMapping("/users")
	public User addUser(@RequestBody User u) {
		
		return userService.addUser(u);
	}
	
	@PutMapping("/users/{userId}")
	public boolean addUserToProject(@RequestBody Project p, @PathVariable long userId) {
		System.out.println("In Users Put   " + p + "    " + userId);
		return userService.addUserToProject(p, userId);
	}
	
	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
}
