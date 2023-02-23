package com.example.IncedoHackathon.dto;

import com.example.IncedoHackathon.Entities.IdeaSubmission;

public class IdeaSubmissionDTO {
	
	String teamName;
	IdeaSubmission ideaSubmission;

	public IdeaSubmissionDTO() {
		super();
	}

	public IdeaSubmissionDTO(String teamName, IdeaSubmission ideaSubmission) {
		super();
		this.teamName = teamName;
		this.ideaSubmission = ideaSubmission;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public IdeaSubmission getIdeaSubmission() {
		return ideaSubmission;
	}

	public void setIdeaSubmission(IdeaSubmission ideaSubmission) {
		this.ideaSubmission = ideaSubmission;
	}

	@Override
	public String toString() {
		return "IdeaSubmissionDTO [teamName=" + teamName + ", ideaSubmission=" + ideaSubmission + "]";
	}


}
