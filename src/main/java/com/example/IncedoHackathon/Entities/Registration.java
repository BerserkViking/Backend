package com.example.IncedoHackathon.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "registration")
public class Registration {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "teamName", nullable = false, unique = true)
	private String teamName;

	@Column(nullable = false, unique = true)
	private String projectTitle;

	@Column(nullable = false)
	private String domain;

	@Column(nullable = false, length = 5)
	private String memberCount;

	@Column(nullable = false, length = 20)
	private String password;

	public Registration(Long id, String teamName, String projectTitle, String domain, String memberCount,
			String password) {
		super();

		this.teamName = teamName;
		this.projectTitle = projectTitle;
		this.domain = domain;
		this.memberCount = memberCount;
		this.password = password;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getProjectTitle() {
		return projectTitle;
	}

	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getMemberCount() {
		return memberCount;
	}

	public void setMemberCount(String memberCount) {
		this.memberCount = memberCount;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Registration() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Registration(String teamName, String projectTitle, String domain, String memberCount, String password) {
		super();
		this.teamName = teamName;
		this.projectTitle = projectTitle;
		this.domain = domain;
		this.memberCount = memberCount;
		this.password = password;
	}

	@Override
	public String toString() {
		return "Registration [id=" + id + ", teamName=" + teamName + ", projectTitle=" + projectTitle + ", domain="
				+ domain + ", memberCount=" + memberCount + ", password=" + password + "]";
	}

}
