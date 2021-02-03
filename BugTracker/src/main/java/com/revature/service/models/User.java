package com.revature.service.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.*;

@Entity @Data @AllArgsConstructor @NoArgsConstructor
public class User {
	
	@Id 
	@Column(name="user_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@NotNull
	@Column(name="email", unique=true)
	private String email;
	
	@Column(name="role_id")
	private Role role;
	
	@ManyToMany
	@JoinColumn(name="project_id")
	private List<Project> projects;
}
