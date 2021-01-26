package com.revature.service.services;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.service.models.Bug;
import com.revature.service.repository.BugRepo;

public class BugServiceImpl implements BugService {

	private static Logger log = Logger.getLogger(BugServiceImpl.class);
	
	@Autowired
	private BugRepo bugRepo;
	
	@Override
	public long addBug(Bug bug) {
		long ret = 0;
		
		// Check bug's fields to be okay
		
		try {	
			Bug b = bugRepo.save(bug);
			
			if (b != null) {
				ret = b.getId();
			}
			
		} catch (Exception e) {
			log.error(e);
			ret = 0;
		}
		
		return ret;
	}

	@Override
	public List<Bug> getAllBugsPerProject(long projectId) {
		
		List<Bug> bugs = null;
		
		try {
			//bugs = bugRepo.findByProjectId(projectId);
			
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Bug> getAllBugsAssignedTo(long userId) {
		
		return null;
	}

	@Override
	public List<Bug> getAllBugsCreatedBy(long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bug getBugById(long bugId) {
		// TODO Auto-generated method stub
		return null;
	}

}
