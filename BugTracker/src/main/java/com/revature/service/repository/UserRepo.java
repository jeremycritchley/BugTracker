package com.revature.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.service.models.Project;
import com.revature.service.models.User;

public interface UserRepo extends JpaRepository<User, Long> {

	public List<User> findAllByProjectsId(long projectId);
	
	public List<Project> findAllProjectsById(long userId);
	
}
