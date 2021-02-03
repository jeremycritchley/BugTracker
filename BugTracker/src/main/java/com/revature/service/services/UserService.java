package com.revature.service.services;

import java.util.List;

import com.revature.service.models.Project;
import com.revature.service.models.User;

public interface UserService {
	
	/*
	 * Returns List of Users Per Project
	 * Returns empty List if no Users but Project exists
	 * Returns null if there is a DB exception
	 */
	public List<User> getUsersPerProject(long projectId);
	
	/*
	 * Returns List of Projects a User is assigned to
	 * Returns empty List if no Users on Project
	 * Returns null if DB exception
	 */
	public List<Project> getProjectsPerUser(long userId);
	
	/*
	 * Returns User with given ID
	 * Returns null if DB exception or ID does not exist
	 */
	public User getUserById(long userId);
	
	/*
	 * Returns new User with generated ID
	 * Returns null if DB exception or missing a required field		
	 */
	public User addUser(User user);
	
	/*
	 * @param	p	: Project to add User to
	 * @param 	userId: ID of User to be added
	 * Returns true if User successfully added to Project
	 * Returns false if DB exception, User does not exist, Project does not exist
	 */
	public boolean addUserToProject(Project p, long userId);
	
	/*
	 * Returns a list of all the Users in DB
	 * Returns null if DB exception
	 */
	public List<User> getAllUsers();
	
}
