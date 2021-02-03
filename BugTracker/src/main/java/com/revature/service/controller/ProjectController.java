package com.revature.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.service.models.Project;
import com.revature.service.services.ProjectService;

@RestController
public class ProjectController {

	@Autowired
	ProjectService pService;
	
	@PostMapping("/projects")
	public Project addProject(@RequestBody Project p) {
		System.out.println("In post    " + p);
		return pService.addProject(p);
	}
	
}
