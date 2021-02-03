package com.revature.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.service.models.Bug;

public interface BugRepo extends JpaRepository<Bug, Long> {
	
	//@Query(value = "select bugs from Project where Project.id = :projectId")
	public List<Bug> findAllByProjectId(long projectId);
	
	//might need to annotate
	//@Query("select * from Bug where Bug.createdBy = :userId")
	public List<Bug> findAllByCreatedById(long userId);
	
	//might need to annotate
	//@Query("select * from Bug where Bug.assignedTo = :userId")
	public List<Bug> findAllByAssignedToId(long userId);
	
	//might need to annotate
	//@Query("select * from Bug where Bug.assignedTo = :userId or Bug.createdBy = :userId")
	public List<Bug> findAllByAssignedToIdOrCreatedById(long userId, long copy);
	
}
