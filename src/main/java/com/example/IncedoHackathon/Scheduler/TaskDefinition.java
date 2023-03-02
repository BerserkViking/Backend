package com.example.IncedoHackathon.Scheduler;

import java.util.List;

public class TaskDefinition {

	private String cronExpression;
	private SubTaskDefinition subTask;

	public String getCronExpression() {
		return cronExpression;
	}
	
	public TaskDefinition() {
		super();
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public SubTaskDefinition getSubTask() {
		return subTask;
	}

	public void setSubTask(SubTaskDefinition subTask) {
		this.subTask = subTask;
	}

	@Override
	public String toString() {
		return "TaskDefinition [cronExpression=" + cronExpression + ", subTask=" + subTask + "]";
	}

}
