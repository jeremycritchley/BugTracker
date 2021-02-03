package com.revature.service.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.service.models.Bug;
import com.revature.service.models.Priority;
import com.revature.service.models.Project;
import com.revature.service.models.Role;
import com.revature.service.models.Status;
import com.revature.service.models.User;
import com.revature.service.repository.BugRepo;
import com.revature.service.services.BugServiceImpl;

public class BugServiceTests {
	
	@Mock
	BugRepo repo;
	
	@Autowired
	@InjectMocks
	BugServiceImpl service;
	
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
	
	private Bug create(int id) {
		Project p = new Project(1, "Project");
		List<Project> projects = new ArrayList<>();
		projects.add(p);
		User created = new User(1, "first", "last", "email", Role.DEV, projects);
		User assigned = new User(2, "first2", "last2", "email2", Role.DEV, projects);
		return new Bug(id, "bug_title", p, created, assigned, new Date(), new Date(), Priority.LOW, Status.NEW, "bug description", null);
	}
	
	/*
	 * Success
	 */
	@Test
	public void addBugSuccess() {
		Bug b = create(0);
		Bug ret = create(1);
		
		Mockito.when(repo.save(b)).thenReturn(ret);
		assertEquals(ret, service.addBug(b));
	}
	
	/*
	 * Repo throws Exception
	 */
	@Test
	public void addBugException() {
		Bug b = create(0);
		
		Mockito.when(repo.save(b)).thenThrow(new IllegalArgumentException());
		assertEquals(null, service.addBug(b));
	}
	
	/*
	 * Success
	 */
	@Test
	public void getAllBugsSuccess() {
		List<Bug> bugs = new ArrayList<>();
		Bug b1 = create(1);
		Bug b2 = create(2);
		bugs.add(b1);
		bugs.add(b2);
		
		Mockito.when(repo.findAllByProjectId(1)).thenReturn(bugs);
		assertEquals(bugs, service.getAllBugsPerProject(1));
	}
	
	/*
	 * Repo throws Exception
	 */
	@Test 
	public void getAllBugsException() {
		Mockito.when(repo.findAllByProjectId(1)).thenThrow(new RuntimeException());
		assertEquals(null, service.getAllBugsPerProject(1));
	}
	
	/*
	 * Repo returns null (project ID does not exist)
	 */
	@Test 
	public void getAllBugsNull() {
		Mockito.when(repo.findAllByProjectId(1)).thenReturn(null);
		assertEquals(null, service.getAllBugsPerProject(1));
	}
	
	/*
	 * Repo returns null (project has no associated bugs)
	 */
	@Test 
	public void getAllBugsEmpty() {
		Mockito.when(repo.findAllByProjectId(1)).thenReturn(new ArrayList<Bug>());
		assertEquals(new ArrayList<Bug>(), service.getAllBugsPerProject(1));
	}
	
	/*
	 * Success
	 */
	@Test
	public void getAllBugsAssociatedWithSuccess() {
		List<Bug> bugs = new ArrayList<>();
		Bug b1 = create(1);
		Bug b2 = create(2);
		bugs.add(b1);
		bugs.add(b2);
		
		Mockito.when(repo.findAllByAssignedToIdOrCreatedById(1, 1)).thenReturn(bugs);
		List<Bug> ret = service.getAllBugsAssociatedWith(1);
		assertEquals(1, ret.get(0).getCreatedBy().getId() == 1 ? ret.get(0).getCreatedBy().getId() : ret.get(0).getAssignedTo().getId());
		assertEquals(1, ret.get(0).getCreatedBy().getId() == 1 ? ret.get(0).getCreatedBy().getId() : ret.get(0).getAssignedTo().getId());
	}
	
	/*
	 * Repo throws Exception
	 */
	@Test
	public void getAllBugsAssociatedWithException() {
		Mockito.when(repo.findAllByAssignedToIdOrCreatedById(1, 1)).thenThrow(new RuntimeException());
		assertEquals(null, service.getAllBugsAssociatedWith(1));
	}
	
	/*
	 * Repo returns Empty List
	 */
	@Test
	public void getAllBugsAssociatedWithEmpty() {
		Mockito.when(repo.findAllByAssignedToIdOrCreatedById(1, 1)).thenReturn(new ArrayList<Bug>());
		assertEquals(new ArrayList<Bug>(), service.getAllBugsAssociatedWith(1));
	}
	
	/*
	 * Success
	 */
	@Test
	public void getAllBugsAssignedToSuccess() {
		List<Bug> bugs = new ArrayList<>();
		Bug b1 = create(1);
		Bug b2 = create(2);
		bugs.add(b1);
		bugs.add(b2);
		
		Mockito.when(repo.findAllByAssignedToId(2)).thenReturn(bugs);
		List<Bug> ret = service.getAllBugsAssignedTo(2);
		assertEquals(2, ret.get(0).getAssignedTo().getId());
		assertEquals(2, ret.get(0).getAssignedTo().getId());
	}
	
	/*
	 * Repo throws Exception
	 */
	@Test
	public void getAllBugsAssignedToException() {
		Mockito.when(repo.findAllByAssignedToId(1)).thenThrow(new RuntimeException());
		assertEquals(null, service.getAllBugsAssignedTo(1));
	}
	
	/*
	 * Repo returns Empty List
	 */
	@Test
	public void getAllBugsAssignedToEmpty() {
		Mockito.when(repo.findAllByAssignedToId(1)).thenReturn(new ArrayList<Bug>());
		assertEquals(new ArrayList<Bug>(), service.getAllBugsAssignedTo(1));
	}
	
	/*
	 * Success
	 */
	@Test
	public void getAllBugsCreatedBySuccess() {
		List<Bug> bugs = new ArrayList<>();
		Bug b1 = create(1);
		Bug b2 = create(2);
		bugs.add(b1);
		bugs.add(b2);
		
		Mockito.when(repo.findAllByCreatedById(1)).thenReturn(bugs);
		List<Bug> ret = service.getAllBugsCreatedBy(1);
		assertEquals(1, ret.get(0).getCreatedBy().getId());
		assertEquals(1, ret.get(0).getCreatedBy().getId());
	}
	
	/*
	 * Repo throws Exception
	 */
	@Test
	public void getAllBugsCreatedByException() {
		Mockito.when(repo.findAllByCreatedById(1)).thenThrow(new RuntimeException());
		assertEquals(null, service.getAllBugsCreatedBy(1));
	}
	
	/*
	 * Repo returns Empty List
	 */
	@Test
	public void getAllBugsCreatedByEmpty() {
		Mockito.when(repo.findAllByCreatedById(1)).thenReturn(new ArrayList<Bug>());
		assertEquals(new ArrayList<Bug>(), service.getAllBugsCreatedBy(1));
	}
	
	/*
	 * Success
	 */
	@Test
	public void getBugByIdSuccess() {
		Bug b = create(1);
		Optional<Bug> opt = Optional.of(b);
		Mockito.when(repo.findById(1L)).thenReturn(opt);
		assertEquals(b, service.getBugById(1L));
	}
	
	/*
	 * Repo throws exception
	 */
	@Test
	public void getBugByIdException() {
		Mockito.when(repo.findById(-1L)).thenThrow(new RuntimeException());
		assertEquals(null, service.getBugById(-1L));
	}
	
	/*
	 * Repo returns empty Optional
	 */
	@Test
	public void getBugByIdEmpty() {
		Optional<Bug> opt = Optional.empty();
		Mockito.when(repo.findById(1L)).thenReturn(opt);
		assertEquals(null, service.getBugById(1L));
	}

}
