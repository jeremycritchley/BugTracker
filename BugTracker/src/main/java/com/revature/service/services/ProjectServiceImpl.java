package com.revature.service.services;

import java.util.List;
import java.util.Optional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.service.models.Project;
import com.revature.service.repository.ProjectRepo;

@Service
public class ProjectServiceImpl implements ProjectService {

	private static Logger log = Logger.getLogger(ProjectServiceImpl.class);
	
	@Autowired
	private ProjectRepo projectRepo;
	
	@Override
	public Project getProjectById(long projectId) {
		Project p = null;
		if (projectId > 0) {
			try {
				Optional<Project> opt = projectRepo.findById(projectId);
				p = opt.get();
			} catch (Exception e) {
				log.error(e);
				p = null;
			}
		}
		return p;
	}


	@Override
	public Project addProject(Project p) {
		Project ret = null;
		try {
			ret = projectRepo.save(p);
		} catch (Exception e) {
			log.error(e);
			ret = null;
		}
		
		return ret;
	}


	@Override
	public List<Project> getAllProjects() {
		try {
			return projectRepo.findAll();
		} catch (Exception e) {
			log.error(e);
			return null;
		}
	}


	@Override
	public List<Project> getAllPerUser(long userId) {
		List<Project> ret = null;
		if (userId > 0) {
			try {
				ret = projectRepo.findAllByUserId(userId);
			} catch (Exception e) {
				log.error(e);
				ret = null;
			}
		}
		
		return ret;
	}

}
