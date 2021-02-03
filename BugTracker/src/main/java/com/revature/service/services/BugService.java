package com.revature.service.services;

import java.util.List;

import com.revature.service.models.Bug;

public interface BugService {
	
	/*
	 * Returns Bug with generated ID
	 * Returns null if bug is missing fields or DB exception
	 */
	public Bug addBug(Bug bug);
	
	/*
	 * Returns all Bugs for the given Project ID
	 * Returns null if Project ID does not exist or DB exception
	 */
	public List<Bug> getAllBugsPerProject(long projectId);
	
	/*
	 * Returns all Bugs created by or assigned to a User by User ID
	 * Returns null if User ID does not exist or DB exception
	 */
	public List<Bug> getAllBugsAssociatedWith(long userId);
	
	/*
	 * Returns all Bugs assigned to a User by User ID
	 * Returns null if User ID does not exist or DB exception
	 */
	public List<Bug> getAllBugsAssignedTo(long userId);
	
	/*
	 * Returns all Bugs created by a User by User ID
	 * Returns null if User ID does not exist or DB exception
	 */
	public List<Bug> getAllBugsCreatedBy(long userId);
	
	/*
	 * Returns Bug with the given ID
	 * Returns null if Bug ID does not exist or DB exception
	 */
	public Bug getBugById(long bugId);

	/*
	 * Returns a List of all Bugs in DB
	 * Returns null if DB exception
	 */
	public List<Bug> getAllBugs();
	
}
