package com.example.IncedoHackathon.Scheduler;

import java.util.List;

public class SubTaskDefinition {

    private String actionType;
    private String data;
    private List<String> assignees;
    
	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public List<String> getAssignees() {
		return assignees;
	}
	public void setAssignees(List<String> assignees) {
		this.assignees = assignees;
	}
    
    
}
