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
import com.revature.service.models.Role;
import com.revature.service.models.User;
import com.revature.service.repository.UserRepo;
import com.revature.service.services.UserServiceImpl;

public class UserServiceTests {

	@Mock
	UserRepo repo;
	
	
	@Autowired
	@InjectMocks
	UserServiceImpl service;
	
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
	 * TEST: getUsersPerProject
	 * Success
	 */
	@Test
	public void getUsersPerProjectSuccess() {
		List<Project> projects = new ArrayList<Project>();
		Project p = new Project(1, "title");
		projects.add(p);
		List<User> users = new ArrayList<User>();
		User u1 = new User(1, "first", "last", "email1", Role.DEV, projects);
		User u2 = new User(2, "first", "last", "email2", Role.DEV, projects);
		users.add(u1);
		users.add(u2);
		
		Mockito.when(repo.findAllByProjectsId(1L)).thenReturn(users);
		
		users = service.getUsersPerProject(1L);
		
		assertEquals(2, users.size());
		assertEquals(1, users.get(0).getProjects().get(0).getId());
		assertEquals(1, users.get(1).getProjects().get(0).getId());
	}
	
	/*
	 * TEST: getUsersPerProject
	 * Repo throws Exception
	 */
	@Test
	public void getUsersPerProjectException() {
		
		Mockito.when(service.getUsersPerProject((long) 1)).thenThrow(new RuntimeException());
		
		assertEquals(null, service.getUsersPerProject((long)1));
	}
	
	/*
	 * TEST: getUsersPerProject
	 * Repo returns NULL
	 */
	@Test
	public void getUsersPerProjectNull() {
		
		Mockito.when(service.getUsersPerProject((long) 1)).thenReturn(null);
		
		assertEquals(null, service.getUsersPerProject((long)1));
	}
	
	/*
	 * TEST: getUsersPerProject
	 * Repo returns Empty List
	 */
	@Test
	public void getUsersPerProjectEmpty() {
		
		Mockito.when(service.getUsersPerProject((long) 1)).thenReturn(new ArrayList<User>());
		
		assertEquals(new ArrayList<User>(), service.getUsersPerProject((long)1));
	}
	
	/*
	 * TEST: getProjectPerUser
	 * Success
	 */
	@Test
	public void getProjectsPerUserSuccess() {
		List<Project> projects = new ArrayList<Project>();
		Project p = new Project(1, "title");
		projects.add(p);
		User u1 = new User(1, "first", "last", "email1", Role.DEV, projects);
		
		Mockito.when(repo.findAllProjectsById((long) 1)).thenReturn(projects);
		
		assertEquals(u1.getProjects(), repo.findAllProjectsById((long) 1));
	}
	
	/*
	 * TEST: getProjectPerUser
	 * Repo throws Exception
	 */
	@Test
	public void getProjectsPerUserException() {
			
		Mockito.when(repo.findAllProjectsById(1L)).thenThrow(new RuntimeException());
		
		assertEquals(null, service.getProjectsPerUser(1L));
	}
	
	/*
	 * TEST: getProjectPerUser
	 * Repo returns NULL
	 */
	@Test
	public void getProjectsPerUserNull() {
		
		Mockito.when(repo.findAllProjectsById(1L)).thenReturn(null);
		
		assertEquals(null, service.getProjectsPerUser(1L));
	}
	
	/*
	 * TEST: getProjectPerUser
	 * Repo returns Empty List
	 */
	@Test
	public void getProjectsPerUserEmpty() {
		
		Mockito.when(repo.findAllProjectsById((long) 1)).thenReturn(new ArrayList<Project>());
		
		assertEquals(new ArrayList<Project>(), service.getProjectsPerUser(1L));
	}
	
	/*
	 * TEST: getUserById
	 * Success
	 */
	@Test
	public void getUserByIdSuccess() {
		List<Project> projects = new ArrayList<Project>();
		Project p = new Project(1, "title");
		projects.add(p);
		User u1 = new User(1, "first", "last", "email1", Role.DEV, projects);
		Optional<User> opt = Optional.of(u1);
		
		Mockito.when(repo.findById((long) 1)).thenReturn(opt);
		
		assertEquals(u1, service.getUserById((long) 1));
	}
	
	/*
	 * TEST: getUserById
	 * Repo throws Exception
	 */
	@Test
	public void getUserByIdException() {
		
		Mockito.when(repo.findById((long) 1)).thenThrow(new RuntimeException());
		
		assertEquals(null, service.getUserById((long) 1));
	}
	
	/*
	 * TEST: getUserById
	 * Repo returns empty optional
	 */
	@Test
	public void getUserByIdEmpty() {
		Optional<User> opt = Optional.empty();
		
		Mockito.when(repo.findById((long) 1)).thenReturn(opt);
		
		assertEquals(null, service.getUserById((long) 1));
	}
	
	/*
	 * TEST: getUserById
	 * Repo returns NULL
	 */
	@Test
	public void getUserByIdNull() {
		
		
		Mockito.when(repo.findById((long) 1)).thenReturn(null);
		
		assertEquals(null, service.getUserById((long) 1));
	}
	
	/*
	 * TEST: addUser
	 * Success
	 */
	@Test
	public void addUserSuccess() {
		List<Project> projects = new ArrayList<Project>();
		Project p = new Project(1, "title");
		projects.add(p);
		User u1 = new User(0, "first", "last", "email1", Role.DEV, projects);
		User u2 = new User(1, "first", "last", "email1", Role.DEV, projects);
		
		Mockito.when(repo.save(u1)).thenReturn(u2);
		
		assertEquals(u2, service.addUser(u1));
		
	}
	
	/*
	 * TEST: addUser
	 * Repo Throws Exception
	 */
	@Test
	public void addUserException() {
		Mockito.when(repo.save(new User())).thenThrow(new RuntimeException());
		
		assertEquals(null, service.addUser(new User()));
	}
	
	/*
	 * TEST: addUserToProject
	 * Success
	 */
	@Test
	public void addUserToProjectSuccess() {
		List<Project> projects = new ArrayList<Project>();
		Project p = new Project(1, "title");
		projects.add(p);
		Project p2 = new Project(2, "title2");
		
		User u = new User(1, "first", "last", "email1", Role.DEV, projects);
		Optional<User> opt = Optional.of(u);
		User u2 = u;
		u2.getProjects().add(p2);
		Mockito.when(repo.findById(1L)).thenReturn(opt);
		Mockito.when(repo.save(u2)).thenReturn(u2);
		
		assertEquals(true, service.addUserToProject(p2, u.getId()));
	}
	
	/*
	 * TEST: addUserToProject
	 * Repo returns Empty Optional
	 */
	@Test
	public void addUserToProjectEmpty() {
		Project p = new Project(1, "title");
		
		Optional<User> opt = Optional.empty();
		
		Mockito.when(repo.findById(1L)).thenReturn(opt);
		
		assertEquals(false, service.addUserToProject(p, 1L));
	}
	
	/*
	 * TEST: addUserToProject
	 * Repo throws Exception
	 */
	@Test
	public void addUserToProjectException() {
		Project p2 = new Project(2, "title2");
				
		Mockito.when(repo.findById(-1L)).thenThrow(new RuntimeException());
		
		assertEquals(false, service.addUserToProject(p2, -1L));
	}
	
	/*
	 * TEST: addUserToProject
	 * Project param is null
	 */
	@Test
	public void addUserToProjectNull() {
		List<Project> projects = new ArrayList<Project>();
		Project p = new Project(1, "title");
		projects.add(p);
		Project p2 = new Project(2, "title2");
		
		User u = new User(1, "first", "last", "email1", Role.DEV, projects);
		Optional<User> opt = Optional.of(u);
		User u2 = u;
		u2.getProjects().add(p2);
		Mockito.when(repo.findById(1L)).thenReturn(opt);
		Mockito.when(repo.save(u2)).thenReturn(u2);
		
		assertEquals(false, service.addUserToProject(null, u.getId()));
	}
	
	/*
	 * TEST: getAllUsers
	 * Success
	 */
	@Test
	public void getAllUsersSuccess() {
		List<Project> projects = new ArrayList<Project>();
		Project p = new Project(1, "title");
		projects.add(p);
		List<User> users = new ArrayList<User>();
		User u1 = new User(1, "first", "last", "email1", Role.DEV, projects);
		User u2 = new User(2, "first", "last", "email2", Role.DEV, projects);
		users.add(u1);
		users.add(u2);
		
		Mockito.when(repo.findAll()).thenReturn(users);
		
		assertEquals(users, service.getAllUsers());
	}
	
	/*
	 * TEST: getAllUsers
	 * Repo throws Exception
	 */
	@Test
	public void getAllUsersException() {
		Mockito.when(repo.findAll()).thenThrow(new RuntimeException());
		
		assertEquals(null, service.getAllUsers());
	}
	
	
	
}
