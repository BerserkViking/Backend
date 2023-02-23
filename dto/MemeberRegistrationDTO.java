package com.example.IncedoHackathon.dto;

import java.util.List;

import com.example.IncedoHackathon.Entities.Member;

public class MemeberRegistrationDTO {
	String teamName;
	List<Member> members;

	public MemeberRegistrationDTO() {
		super();
	}

	public MemeberRegistrationDTO(String teamName, List<Member> members) {
		super();
		this.teamName = teamName;
		this.members = members;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public List<Member> getMembers() {
		return members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}

	@Override
	public String toString() {
		return "MemeberRegistrationDTO [teamName=" + teamName + "]";
	}

}
