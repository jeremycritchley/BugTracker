package com.revature.service.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.revature.service.models.Project;

public interface ProjectRepo extends JpaRepository<Project, Long> {
	
	@Query(value = "select projects from User where user_id = ?1")
	public List<Project> findAllByUserId(long userId);
	
}
