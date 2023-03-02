package com.example.IncedoHackathon.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "judgeratings")
public class JudgeRatings {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "codeQuality")
	private String codeQuality;

	@Column(name = "UI/UX")
	private String userInterface;

	@Column(name = "Speed")
	private String speed;

	@Column(name = "Usability")
	private String usability;

	@Column(name = "Average")
	private String average;

//	@ManyToOne
//	@JoinColumn(referencedColumnName = "teamName", name = "teamName")
	private String teamName;

public JudgeRatings() {
	super();
}

public JudgeRatings(Long id, String codeQuality, String userInterface, String speed, String usability, String average,
		String teamName) {
	super();
	this.id = id;
	this.codeQuality = codeQuality;
	this.userInterface = userInterface;
	this.speed = speed;
	this.usability = usability;
	this.average = average;
	this.teamName = teamName;
}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getCodeQuality() {
	return codeQuality;
}

public void setCodeQuality(String codeQuality) {
	this.codeQuality = codeQuality;
}

public String getUserInterface() {
	return userInterface;
}

public void setUserInterface(String userInterface) {
	this.userInterface = userInterface;
}

public String getSpeed() {
	return speed;
}

public void setSpeed(String speed) {
	this.speed = speed;
}

public String getUsability() {
	return usability;
}

public void setUsability(String usability) {
	this.usability = usability;
}

public String getAverage() {
	return average;
}

public void setAverage(String average) {
	this.average = average;
}

public String getTeamName() {
	return teamName;
}

public void setTeamName(String teamName) {
	this.teamName = teamName;
}

@Override
public String toString() {
	return "JudgeRatings [id=" + id + ", codeQuality=" + codeQuality + ", userInterface=" + userInterface + ", speed="
			+ speed + ", usability=" + usability + ", average=" + average + ", teamName=" + teamName + "]";
}

	
}
