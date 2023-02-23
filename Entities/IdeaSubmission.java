package com.example.IncedoHackathon.Entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
@Entity
@Table(name = "ideaSubmission")
public class IdeaSubmission {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String problemStatement;

	@Column(nullable = false)
	private String problemSolution;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "userName", name = "userName")
	private Panelist panelistAssign;
	
	@Column(nullable = true)
	private String status ;
	
	@Column
	private String feedback ;
	
	@Column(nullable = true)
	private String finalSubmission;

	@OneToOne(cascade =CascadeType.PERSIST )
	@JoinColumn(referencedColumnName = "teamName", name = "teamName")
	private Registration registration;

	public IdeaSubmission() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProblemStatement() {
		return problemStatement;
	}

	public void setProblemStatement(String problemStatement) {
		this.problemStatement = problemStatement;
	}
	
	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public String getProblemSolution() {
		return problemSolution;
	}

	public void setProblemSolution(String problemSolution) {
		this.problemSolution = problemSolution;
	}

	public Panelist getPanelistAssign() {
		return panelistAssign;
	}

	public void setPanelistAssign(Panelist panelistAssign) {
		this.panelistAssign = panelistAssign;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFinalSubmission() {
		return finalSubmission;
	}

	public void setFinalSubmission(String finalSubmission) {
		this.finalSubmission = finalSubmission;
	}

	public Registration getRegistration() {
		return registration;
	}

	public void setRegistration(Registration registration) {
		this.registration = registration;
	}

	public IdeaSubmission(Long id, String problemStatement, String problemSolution, Panelist panelistAssign,
			String status, String finalSubmission, Registration registration,String feedback) {
		super();
		this.id = id;
		this.problemStatement = problemStatement;
		this.problemSolution = problemSolution;
		this.panelistAssign = panelistAssign;
		this.status = status;
		this.finalSubmission = finalSubmission;
		this.registration = registration;
		this.feedback=feedback;
	}

	@Override
	public String toString() {
		return "IdeaSubmission [id=" + id + ", problemStatement=" + problemStatement + ", problemSolution="
				+ problemSolution + ", panelistAssign=" + panelistAssign + ", status=" + status + ", finalSubmission="
				+ finalSubmission + ", registration=" + registration + ",feedback="+ feedback +"]";
	}

  

}
