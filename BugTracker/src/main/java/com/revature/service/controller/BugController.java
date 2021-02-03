package com.revature.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.service.models.Bug;
import com.revature.service.services.BugService;

@RestController
public class BugController {
	
	@Autowired
	BugService service;
	
	@PostMapping("/bugs")
	public ResponseEntity<Bug> create(@RequestBody Bug bug) {
		ResponseEntity<Bug> ret = null;
		bug = service.addBug(bug);
		if (bug == null) {
			ret = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		} else {
			ret = ResponseEntity.status(HttpStatus.CREATED).body(bug);
		}
		return ret;
	}
	
	@GetMapping("/projects/{projectId}/bugs")
	public ResponseEntity<List<Bug>> getProjectBugs(@PathVariable long projectId) {
		ResponseEntity<List<Bug>> ret = null;
		List<Bug> bugs = service.getAllBugsPerProject(projectId);
		if (bugs == null) {
			ret = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		} else if (bugs.size() == 0) {
			ret = ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} else {
			ret = ResponseEntity.status(HttpStatus.OK).body(bugs);
		}
		return ret;
	}
	
	@GetMapping("/bugs/all/{userId}")
	public ResponseEntity<List<Bug>> getBugsAssociatedWithUser(@PathVariable long userId) {
		ResponseEntity<List<Bug>> ret = null;
		List<Bug> bugs = service.getAllBugsAssociatedWith(userId);
		if (bugs == null) {
			ret = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		} else if (bugs.size() == 0) {
			ret = ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} else {
			ret = ResponseEntity.status(HttpStatus.OK).body(bugs);
		}
		return ret;
	}
	
	@GetMapping("/bugs/created/{userId}")
	public ResponseEntity<List<Bug>> getBugsCreatedBy(@PathVariable long userId) {
		ResponseEntity<List<Bug>> ret = null;
		List<Bug> bugs = service.getAllBugsCreatedBy(userId);
		if (bugs == null) {
			ret = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		} else if (bugs.size() == 0) {
			ret = ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} else {
			ret = ResponseEntity.status(HttpStatus.OK).body(bugs);
		}
		return ret;
	}
	
	@GetMapping("/bugs/assigned/{userId}")
	public ResponseEntity<List<Bug>> getBugsAssignedTo(@PathVariable long userId) {
		ResponseEntity<List<Bug>> ret = null;
		List<Bug> bugs = service.getAllBugsAssignedTo(userId);
		if (bugs == null) {
			ret = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		} else if (bugs.size() == 0) {
			ret = ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} else {
			ret = ResponseEntity.status(HttpStatus.OK).body(bugs);
		}
		return ret;
	}
	
	@GetMapping("/bugs/{bugId}")
	public ResponseEntity<Bug> getBug(@PathVariable long bugId) {
		ResponseEntity<Bug> ret = null;
		Bug bug = service.getBugById(bugId);
		if (bug == null) {
			ret = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		} else {
			ret = ResponseEntity.status(HttpStatus.OK).body(bug);
		}
		return ret;
	}
	
	@GetMapping("/bugs")
	public ResponseEntity<List<Bug>> getAll() {
		ResponseEntity<List<Bug>> ret = null;
		List<Bug> bugs = service.getAllBugs();
		if (bugs == null) {
			ret = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		} else if (bugs.size() == 0) {
			ret = ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} else {
			ret = ResponseEntity.status(HttpStatus.OK).body(bugs);
		}
		return ret;
	}
	
	@PutMapping("/bugs")
	public ResponseEntity<Bug> update(@RequestBody Bug bug) {
		ResponseEntity<Bug> ret = null;
		bug = service.updateBug(bug);
		if (bug == null) {
			ret = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		} else {
			ret = ResponseEntity.status(HttpStatus.OK).body(bug);
		}
		return ret;
	}
	
	
}
