package com.revature.service.services;

import java.util.List;

import com.revature.service.models.Project;

public interface ProjectService {

	/*
	 * Returns Project with generated ID 
	 * Returns null if DB exception or missing fields
	 */
	public Project addProject(Project p);
	
	/*
	 * Returns Project with given ID
	 * Returns null if DB exception or Project with given ID does not exist
	 */
	public Project getProjectById(long projectId);
	
	/*
	 * Returns a list of all Projects in DB
	 * Returns null if DB exception
	 */
	public List<Project> getAllProjects();
	
	public List<Project> getAllPerUser(long userId);
		
}
