package com.revature.service.services;

import java.util.List;

import com.revature.service.models.Project;

public interface ProjectService {

	public Project getProjectById(long projectId);
	
	public List<Project> getProjectsByUserId(long userId);
	
}
