package com.revature.service.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity @Data
public class Project {
	
	@Id 
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "name")
	private String name;
	
	@ManyToMany
	@JoinColumn(name="user_id")
	private List<User> users;
	
//	@OneToMany
//	@JoinColumn(name="project_id")
//	private List<Bug> bugs;
	
}
