package com.company.WebNestApp.model;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="task")
public class Task {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String status; // "Pending", "In Progress", "Completed", "Delayed"
    private Date startDate;
    private Date dueDate;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user; // User assigned to the task

    @OneToMany(mappedBy = "task")
    private List<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "assigned_to_id") 
    private User assignedTo;

    @ManyToOne
    @JoinColumn(name = "assigned_by_id") 
    private User assignedBy;
    
	public Task() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public Task(Long id, String title, String description, String status, Date startDate, Date dueDate, User user,
			List<Comment> comments, User assignedTo, User assignedBy) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.status = status;
		this.startDate = startDate;
		this.dueDate = dueDate;
		this.user = user;
		this.comments = comments;
		this.assignedTo = assignedTo;
		this.assignedBy = assignedBy;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	public User getAssignedTo() { return assignedTo; }
    public void setAssignedTo(User assignedTo) { this.assignedTo = assignedTo; }
    
    public User getAssignedBy() { return assignedBy; }
    public void setAssignedBy(User assignedBy) { this.assignedBy = assignedBy; }

	@Override
	public String toString() {
		return "Task [id=" + id + ", title=" + title + ", description=" + description + ", status=" + status
				+ ", startDate=" + startDate + ", dueDate=" + dueDate + ", user=" + user + ", comments=" + comments
				+ ", assignedTo=" + assignedTo + ", assignedBy=" + assignedBy + "]";
	}

	
    
}
