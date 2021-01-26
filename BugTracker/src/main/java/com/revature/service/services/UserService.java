package com.revature.service.services;

import java.util.List;

import com.revature.service.models.User;

public interface UserService {
	
	public List<User> getUsersPerProject(long projectId);
	
	public User getUserById(long userId);
	
	public long addUser(User user);
	
}
