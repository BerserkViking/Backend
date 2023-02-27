package com.example.IncedoHackathon.Scheduler;

import java.util.List;

public class TaskDefinition {

	private String cronExpression;
	private List<SubTaskDefinition> subTasks;

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public List<SubTaskDefinition> getSubTasks() {
		return subTasks;
	}

	public void setSubTasks(List<SubTaskDefinition> subTasks) {
		this.subTasks = subTasks;
	}

}
