package com.revature.service.services;

import java.util.List;
import java.util.Optional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.service.models.Bug;
import com.revature.service.repository.BugRepo;

@Service
public class BugServiceImpl implements BugService {

	private static Logger log = Logger.getLogger(BugServiceImpl.class);
	
	@Autowired
	private BugRepo bugRepo;
	
	@Override
	public Bug addBug(Bug bug) {
		Bug ret = null;
		
		// Check bug's fields to be okay
		
		try {	
			ret = bugRepo.save(bug);
			
		} catch (Exception e) {
			log.error(e);
			ret = null;
		}
		
		return ret;
	}

	@Override
	public List<Bug> getAllBugsPerProject(long projectId) {
		
		List<Bug> bugs = null;
		
		try {
			bugs = bugRepo.findAllByProjectId(projectId);
			
		} catch (Exception e) {
			log.error(e);
			bugs = null;
		}
		
		return bugs;
	}

	/*
	 * Get all bugs that are created by or assigned to the user
	 */
	@Override
	public List<Bug> getAllBugsAssociatedWith(long userId) {
		
		List<Bug> bugs = null;
		
		try {
			bugs = bugRepo.findAllByAssignedToIdOrCreatedById(userId, userId);
		} catch (Exception e) {
			log.error(e);
			bugs = null;
		}
		
		return bugs;
	}

	@Override
	public List<Bug> getAllBugsAssignedTo(long userId) {
		List<Bug> bugs = null;
		
		try {
			bugs = bugRepo.findAllByAssignedToId(userId);
		} catch (Exception e) {
			log.error(e);
			bugs = null;
		}
		
		return bugs;
	}

	@Override
	public List<Bug> getAllBugsCreatedBy(long userId) {
		List<Bug> bugs = null;
		
		try {
			bugs = bugRepo.findAllByCreatedById(userId);
		} catch (Exception e) {
			log.error(e);
			bugs = null;
		}
		
		return bugs;
	}

	@Override
	public Bug getBugById(long bugId) {
		Optional<Bug> bug = null;
		Bug b = null;
		
		try {
			bug = bugRepo.findById(bugId);
			b = bug.get();
		} catch (Exception e) {
			log.error(e);
			b = null;
		}
		
		return b;
	}
	
	@Override
	public List<Bug> getAllBugs() {
		try {
			return bugRepo.findAll();
		} catch (Exception e) {
			log.error(e);
			return null;
		}
	}

	@Override
	public Bug updateBug(Bug bug) {
		Bug ret = null;
		try {
			Bug b = bugRepo.findById(bug.getId()).get();
			if (b != null) {
				ret = bugRepo.save(bug);
			}
		} catch (Exception e) {
			log.error(e);
			ret = null;
		}
		return ret;
	}

}
