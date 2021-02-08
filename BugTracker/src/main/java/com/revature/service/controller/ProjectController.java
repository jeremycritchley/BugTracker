package com.revature.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.service.models.Project;
import com.revature.service.services.ProjectService;

@RestController
public class ProjectController {

	@Autowired
	ProjectService service;
	
	@PostMapping("/projects")
	public ResponseEntity<Project> addProject(@RequestBody Project p) {
		ResponseEntity<Project> ret = null;
		Project newProject = service.addProject(p);
		if (newProject == null) {
			ret = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		} else {
			ret = ResponseEntity.status(HttpStatus.CREATED).body(newProject);
		}
		
		return ret;
	}
	
	@GetMapping("/projects/{projectId}")
	public ResponseEntity<Project> getProject(@PathVariable long projectId) {
		ResponseEntity<Project> ret = null;
		Project proj = service.getProjectById(projectId);
		if (proj == null) {
			ret = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		} else {
			ret = ResponseEntity.status(HttpStatus.CREATED).body(proj);
		}
		
		return ret;
	}
	
	@GetMapping("/projects")
	public ResponseEntity<List<Project>> getProject() {
		ResponseEntity<List<Project>> ret = null;
		List<Project> projects = service.getAllProjects();
		if (projects == null) {
			ret = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		} else if (projects.size() == 0) {
			ret = ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} else {
			ret = ResponseEntity.status(HttpStatus.CREATED).body(projects);
		}
		
		return ret;
	}
	
	@GetMapping("/users/{userId}/projects")
	public ResponseEntity<List<Project>> getUserProjects(@PathVariable long userId) {
		ResponseEntity<List<Project>> ret = null;
		List<Project> projects = service.getAllPerUser(userId);
		if (projects == null) {
			ret = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		} else if (projects.size() == 0) {
			ret = ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} else {
			ret = ResponseEntity.status(HttpStatus.OK).body(projects);
		}
		return ret;
	}
	
}
