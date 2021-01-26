package com.revature.service.services;

import java.util.List;

import com.revature.service.models.Bug;

public interface BugService {
	
	public long addBug(Bug bug);
	
	public List<Bug> getAllBugsPerProject(long projectId);
	
	public List<Bug> getAllBugsAssociatedWith(long userId);
	
	public List<Bug> getAllBugsAssignedTo(long userId);
	
	public List<Bug> getAllBugsCreatedBy(long userId);
	
	public Bug getBugById(long bugId);
	
}
