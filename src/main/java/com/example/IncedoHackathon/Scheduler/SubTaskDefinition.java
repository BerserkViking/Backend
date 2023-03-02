package com.example.IncedoHackathon.Scheduler;

public class SubTaskDefinition {

	private String actionType;

	private long interval;

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public long getInterval() {
		return interval;
	}

	public void setInterval(long interval) {
		this.interval = interval;
	}

	@Override
	public String toString() {
		return "SubTaskDefinition [actionType=" + actionType + ", interval=" + interval + "]";
	}

}
