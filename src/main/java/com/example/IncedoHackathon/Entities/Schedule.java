package com.example.IncedoHackathon.Entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "schedule")
public class Schedule {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;

	@Column(name = "starttime")
	private LocalDateTime startTime;

	@Column(name = "endtime")
	private LocalDateTime endTime;

	@Column(name = "event")
	private String event;

	@OneToOne
	@JoinColumn(name = "ideaSubmissionId", referencedColumnName = "id")
	IdeaSubmission ideaSubmission;

	@Column(name = "ideaScheduleCounter")
	int ideaScheduleCounter;

	public Schedule() {
		super();
	}

	public Schedule(LocalDateTime startTime, LocalDateTime endTime, String event) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.event = event;
	}

	public Schedule(LocalDateTime startTime, LocalDateTime endTime, String event, int ideaScheduleCounter) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.event = event;
		this.ideaScheduleCounter = ideaScheduleCounter;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public IdeaSubmission getIdeaSubmission() {
		return ideaSubmission;
	}

	public void setIdeaSubmission(IdeaSubmission ideaSubmission) {
		this.ideaSubmission = ideaSubmission;
	}

	public int getIdeaScheduleCounter() {
		return ideaScheduleCounter;
	}

	public void setIdeaScheduleCounter(int ideaScheduleCounter) {
		this.ideaScheduleCounter = ideaScheduleCounter;
	}

	@Override
	public String toString() {
		return "Schedule [id=" + id + ", startTime=" + startTime + ", endTime=" + endTime + ", event=" + event
				+ ", ideaSubmission=" + ideaSubmission + ", ideaScheduleCounter=" + ideaScheduleCounter + "]";
	}

}
