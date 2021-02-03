package com.revature.service.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.service.models.Project;
import com.revature.service.repository.ProjectRepo;
import com.revature.service.services.ProjectServiceImpl;

public class ProjectServiceTests {
	@Mock
	ProjectRepo repo;
	
	@Autowired
	@InjectMocks
	ProjectServiceImpl service;
	
	@BeforeEach
	@SuppressWarnings("deprecation")
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	/*
	 * Sanity check
	 */
	@Test
	void contextLoads() {
	}
	
	/*
	 * Success
	 */
	@Test
	public void addProjectSuccess() {
		Project p = new Project(0, "name");
		Project pRet = new Project(1, "name");
		
		Mockito.when(repo.save(p)).thenReturn(pRet);
		
		assertEquals(pRet, service.addProject(p));
	}
	
	/*
	 * Repo throws Exception
	 */
	@Test
	public void addProjectException() {
		Project p = new Project(0, "name");
		
		Mockito.when(repo.save(p)).thenThrow(new RuntimeException());
		
		assertEquals(null, service.addProject(p));
	}

	/*
	 * Repo returns null
	 */
	@Test
	public void addProjectNull() {
		Project p = new Project(0, "name");
		
		Mockito.when(repo.save(p)).thenReturn(null);
		
		assertEquals(null, service.addProject(p));
	}
	
	/*
	 * Success
	 */
	@Test
	public void getProjectByIdSuccess() {
		Project p = new Project(1, "name");
		Optional<Project> op = Optional.of(p);
		
		Mockito.when(repo.findById((long) 1)).thenReturn(op);
		
		assertEquals(p, service.getProjectById((long) 1));
	}
	
	/*
	 * Exception - return null
	 */
	@Test
	public void getProjectByIdException() {
		
		Mockito.when(repo.findById((long) 1)).thenThrow(new RuntimeException());
		
		assertEquals(null, service.getProjectById((long) 1));
	}
	
	/*
	 * ID not found, return null
	 */
	@Test
	public void getProjectByIdNotFound() {
		Mockito.when(repo.findById((long) 1)).thenReturn(null);
		
		assertEquals(null, service.getProjectById((long) 1));
	}
	
	/*
	 * Success
	 */
	@Test
	public void getAllProjectsSuccess() {
		List<Project> projects = new ArrayList<Project>();
		Project p1 = new Project(1, "title");
		Project p2 = new Project(2, "title2");
		projects.add(p1);
		projects.add(p2);
		
		Mockito.when(repo.findAll()).thenReturn(projects);
		
		assertEquals(projects, service.getAllProjects());
	}
	
	/*
	 * Exception
	 */
	@Test
	public void getAllProjectsException() {
		
		Mockito.when(repo.findAll()).thenThrow(new RuntimeException());
		
		assertEquals(null, service.getAllProjects());
	}
	
	/*
	 * Empty List
	 */
	@Test
	public void getAllProjectsEmpty() {
		List<Project> projects = new ArrayList<Project>();
		
		Mockito.when(repo.findAll()).thenReturn(projects);
		
		assertEquals(projects, service.getAllProjects());
	}
	
	
}
