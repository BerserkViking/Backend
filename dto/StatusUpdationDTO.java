package com.example.IncedoHackathon.dto;

public class StatusUpdationDTO {
	Long ideaSubmissionId;
	String status;
	String feedback;
	
	
	public StatusUpdationDTO() {
		super();
	}


	public StatusUpdationDTO(Long ideaSubmissionId, String status, String feedback) {
		super();
		this.ideaSubmissionId = ideaSubmissionId;
		this.status = status;
		this.feedback = feedback;
	}


	public Long getIdeaSubmissionId() {
		return ideaSubmissionId;
	}


	public void setIdeaSubmissionId(Long ideaSubmissionId) {
		this.ideaSubmissionId = ideaSubmissionId;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getFeedback() {
		return feedback;
	}


	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}


	@Override
	public String toString() {
		return "StatusUpdationDTO [ideaSubmissionId=" + ideaSubmissionId + ", status=" + status + ", feedback="
				+ feedback + "]";
	}


}
