package com.revature.service.models;

import java.sql.Blob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity @Data
public class Bug {
	
	@Id 
	@Column(name="user_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="title", unique=true)
	private String title;
	
	@ManyToOne
	@JoinColumn(name="project_id")
	private Project project;
	
	@ManyToOne
	@JoinColumn(name="created_by")
	private User createdBy;
	
	@ManyToOne
	@JoinColumn(name="assigned_to")
	private User assignedTo;
	
	@Column(name="created_on")
	private Date createdOn;
	
	@Column(name="last_updated")
	private Date lastUpdated;
	
	@Column(name = "priority")
	private Priority priority;
	
	@Column(name="status")
	private Status status;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "image", nullable=true)
	private Blob image;
	
}
