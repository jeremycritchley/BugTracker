package com.revature.service.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.service.models.Project;
import com.revature.service.models.User;
import com.revature.service.repository.UserRepo;

@Service
public class UserServiceImpl implements UserService {

	private static Logger log = Logger.getLogger(UserServiceImpl.class);
	
	@Autowired
	UserRepo userRepo;
	
	@Override
	public List<User> getUsersPerProject(long projectId) {
		List<User> users = null;
		if (projectId > 0) {
			try {	
				users = userRepo.findAllByProjectsId(projectId);
			} catch (Exception e) {
				log.error(e);
				users = null;
			}
		}
		
		return users;
	}

	@Override
	public User getUserById(long userId) {
		Optional<User> u = null;
		User user = null;
		if (userId > 0) {
			try {
				u = userRepo.findById(userId);
				user = u.get();
			} catch (Exception e) {
				log.error(e);
				user = null;
			}
		}
		return user;
	}

	@Override
	public User addUser(User user) {
		User ret = null;
		try {
			if (user.getEmail().equals(null)) { }
			else if (user.getFirstName().equals(null)){ }
			else if (user.getLastName().equals(null)){ }
			else if (user.getFirstName().equals(null)){ }
			else if (user.getRole().equals(null)){ }
			else {
				ret = userRepo.save(user);
				if (ret.getId() < 1) {
					ret = null;
				}
			}
		} catch (Exception e) {
			log.error(e);
			ret = null;
		}
		
		return ret;
	}

	@Override
	public boolean addUserToProject(Project p, long userId) {
		boolean ret = false;
		if (p != null && userId > 0) {
			Optional<User> userOpt = null;
			userOpt = userRepo.findById(userId);
			try {
				User u = userOpt.get();
				List<Project> ps = u.getProjects();
				if (ps != null) {
					ps.add(p);
				} else {
					ps = new ArrayList<Project>();
					ps.add(p);
				}
				u.setProjects(ps);
				userRepo.save(u);
				ret = true;
			} catch (Exception e) {
				log.error(e);
				ret = false;
			}
		}
		return ret;
	}
	
	public List<User> getAllUsers() {
		try {
			return userRepo.findAll();
		} catch (Exception e) {
			return null;
		}
		
	}

	@Override
	public List<Project> getProjectsPerUser(long userId) {
		List<Project> projects = null;
		try {
			if (userId > 0) {
				projects = userRepo.findAllProjectsById(userId);
			}
		} catch (Exception e) {
			log.error(e);
			projects = null;
		}
		return projects;
	}

}
