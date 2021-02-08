package com.revature.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.service.models.Project;
import com.revature.service.models.Role;
import com.revature.service.models.User;
import com.revature.service.services.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	@PostMapping("/users")
	public ResponseEntity<User> addUser(@RequestBody User u) {
		System.out.println("Adding a User");
		ResponseEntity<User> ret = null;
		User newUser = userService.addUser(u);
		if (newUser == null) {
			ret = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		} else {
			ret = ResponseEntity.status(HttpStatus.CREATED).body(newUser);
		}
		return ret;
	}
	
	@PutMapping("/users/{userId}")
	public ResponseEntity<Boolean> addUserToProject(@RequestBody Project p, @PathVariable long userId) {
		ResponseEntity<Boolean> ret = null;
		boolean b = userService.addUserToProject(p, userId);
		if (b) {
			ret = ResponseEntity.status(HttpStatus.OK).body(true);
		} else {
			ret = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
		}
		return ret;
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUsers() {
		ResponseEntity<List<User>> ret = null;
		List<User> users = userService.getAllUsers();
		if (users == null) {
			ret = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		} else if (users.size() == 0) {
			ret = ResponseEntity.status(HttpStatus.NO_CONTENT).body(users);
		} else {
			ret = ResponseEntity.status(HttpStatus.OK).body(users);
		}
		return ret;
	}
	
	@GetMapping("/users/{userId}")
	public ResponseEntity<User> getUser(@PathVariable long userId) {
		ResponseEntity<User> ret = null;
		User u = userService.getUserById(userId);
		if (u == null) {
			ret = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		} else {
			ret = ResponseEntity.status(HttpStatus.OK).body(u);
		}
		return ret;
	}
	
	
	@GetMapping("/projects/{projectId}/users")
	public ResponseEntity<List<User>> getProjectUsers(@PathVariable long projectId) {
		ResponseEntity<List<User>> ret = null;
		List<User> users = userService.getUsersPerProject(projectId);
		if (users == null) {
			ret = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		} else if (users.size() == 0) {
			ret = ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} else {
			ret = ResponseEntity.status(HttpStatus.OK).body(users);
		}
		return ret;
	}
}
