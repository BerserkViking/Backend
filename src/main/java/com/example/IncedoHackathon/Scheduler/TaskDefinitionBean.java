package com.example.IncedoHackathon.Scheduler;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TaskDefinitionBean implements Runnable {

	private TaskDefinition taskDefinition;

	@Override
	public void run() {
		List<SubTaskDefinition> subTasks = taskDefinition.getSubTasks();
		if (subTasks == null) {
			return;
		}
		for (SubTaskDefinition subTask : subTasks) {
			String actionType = subTask.getActionType();
			String data = subTask.getData();
			List<String> assignees = subTask.getAssignees();
			if (assignees == null || assignees.isEmpty()) {
				// no assignees, execute the sub-task once
				executeSubTask(actionType, data);
			} else {
				// execute the sub-task for each assignee
				for (String assignee : assignees) {
					executeSubTask(actionType, data + " assigned to " + assignee);
				}
			}
		}
	}

	private void executeSubTask(String actionType, String data) {
		System.out.println("Running action: " + actionType);
		System.out.println("With Data: " + data);
	}

	public TaskDefinition getTaskDefinition() {
		return taskDefinition;
	}

	public void setTaskDefinition(TaskDefinition taskDefinition) {
		this.taskDefinition = taskDefinition;
	}
}
