package com.revature.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.service.models.Project;

public interface ProjectRepo extends JpaRepository<Project, Long> {
	
	
}
