package com.revature.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.revature.service.models.Bug;

public interface BugRepo extends JpaRepository<Bug, Long> {
	
	//@Query(value = "select bugs from Project where Project.id = :projectId")
	public List<Bug> findByProjectId(long projectId);
	
	//might need to annotate
	//@Query("select * from Bug where Bug.createdBy = :userId")
	public List<Bug> findByCreatedBy(long userId);
	
	//might need to annotate
	//@Query("select * from Bug where Bug.assignedTo = :userId")
	public List<Bug> findByAssignedTo(long userId);
	
	//might need to annotate
	//@Query("select * from Bug where Bug.assignedTo = :userId or Bug.createdBy = :userId")
	public List<Bug> findByAssignedToIdOrCreatedBy(long userId, long copy);
	
}
