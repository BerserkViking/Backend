package com.example.IncedoHackathon.Scheduler;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.IncedoHackathon.Entities.IdeaSubmission;
import com.example.IncedoHackathon.Entities.Schedule;
import com.example.IncedoHackathon.Repositories.IdeaSubmissionRepository;
import com.example.IncedoHackathon.Repositories.ScheduleRepository;

@Service
public class TaskDefinitionBean implements Runnable {
	@Autowired
	ScheduleRepository scheduleRepository;

	@Autowired
	TaskSchedulingService taskSchedulingService;

	@Autowired
	IdeaSubmissionRepository ideaSubmissionRepository;

	private TaskDefinition taskDefinition;

	@Override
	public void run() {
		SubTaskDefinition subTaskDefinition = taskDefinition.getSubTask();
		if (subTaskDefinition == null) {
			return;
		}
		executeSubTask(subTaskDefinition);

	}

	public Long convertMinutesToMilliSeconds(Long minutes) {
		return 60 * 1000 * minutes;
	}

	public void removeAllJob() {
		taskSchedulingService.removeScheduledTask();
	}

	public void setNextIdeaSubmission(SubTaskDefinition subTaskDefinition) {

		Schedule projectSchedule = new Schedule();
		Optional<Schedule> projectScheduleOptional = scheduleRepository.findByEvent("ProjectEvaluation");
		List<IdeaSubmission> sortedListOfIdeas = ideaSubmissionRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));

		sortedListOfIdeas = sortedListOfIdeas.stream().filter((project) -> {
			if (project.getStatus() != null)
				return project.getStatus().equals("Accept");
			else {
				return false;
			}
		}).toList();
		if (projectScheduleOptional.isPresent()) {
			projectSchedule = projectScheduleOptional.get();
			int takeCount = projectSchedule.getIdeaScheduleCounter();
			if (sortedListOfIdeas.get(takeCount) == null) {
				removeAllJob();
			}
			LocalDateTime nextIdeaStartTime = projectSchedule.getEndTime();
			LocalDateTime nextIdeaEndTime = new Timestamp(Timestamp.valueOf(projectSchedule.getEndTime()).getTime()
					+ convertMinutesToMilliSeconds(subTaskDefinition.getInterval())).toLocalDateTime();
			projectSchedule.setStartTime(nextIdeaStartTime);
			projectSchedule.setEndTime(nextIdeaEndTime);
			projectSchedule.setIdeaSubmission(sortedListOfIdeas.get(takeCount));
			projectSchedule.setIdeaScheduleCounter(takeCount + 1);
			scheduleRepository.save(projectSchedule);
		} else {
			Schedule ideaSubmissionSchedule = scheduleRepository.findByEvent("IdeaSubmission").orElse(null);
			LocalDateTime schedulerStartTime = LocalDateTime.now();
			projectSchedule = new Schedule(ideaSubmissionSchedule.getEndTime(),
					new Timestamp(Timestamp.valueOf(schedulerStartTime).getTime()
							+ convertMinutesToMilliSeconds(subTaskDefinition.getInterval())).toLocalDateTime(),
					"ProjectEvaluation", 1);
			projectSchedule.setIdeaSubmission(sortedListOfIdeas.get(0));
			scheduleRepository.save(projectSchedule);
		}

	}

	private void executeSubTask(SubTaskDefinition subTaskDefinition) {
		System.out.println("STARTING EXECUTION");
		setNextIdeaSubmission(subTaskDefinition);
		System.out.println("DONE");
	}

	public TaskDefinition getTaskDefinition() {
		return taskDefinition;
	}

	public void setTaskDefinition(TaskDefinition taskDefinition) {
		this.taskDefinition = taskDefinition;
	}
}
