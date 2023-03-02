package com.example.IncedoHackathon.Scheduler;

import org.hibernate.id.uuid.UuidGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "/api/schedule")
public class JobSchedulingController {

	@Autowired
	private TaskSchedulingService taskSchedulingService;

	@Autowired
	private TaskDefinitionBean taskDefinitionBean;

	/**
	 * Generate a CRON expression is a string comprising 6 or 7 fields separated by
	 * white space.
	 *
	 * @param seconds    mandatory = yes. allowed values = {@code  0-59    * / , -}
	 * @param minutes    mandatory = yes. allowed values = {@code  0-59    * / , -}
	 * @param hours      mandatory = yes. allowed values = {@code 0-23   * / , -}
	 * @param dayOfMonth mandatory = yes. allowed values =
	 *                   {@code 1-31  * / , - ? L W}
	 * @param month      mandatory = yes. allowed values =
	 *                   {@code 1-12 or JAN-DEC    * / , -}
	 * @param dayOfWeek  mandatory = yes. allowed values =
	 *                   {@code 0-6 or SUN-SAT * / , - ? L #}
	 * @param year       mandatory = no. allowed values =
	 *                   {@code 1970–2099    * / , -}
	 * @return a CRON Formatted String.
	 */
	private static String generateCronExpression(final String minutes, final String hours, final String dayOfMonth,
			final String month, final String dayOfWeek, final String year) {
		return String.format("%1$s %2$s %3$s %4$s %5$s %6$s", minutes, hours, dayOfMonth, month, dayOfWeek, year);
	}

	@PostMapping(path = "/taskdef")
	public void scheduleATask() {
		TaskDefinition taskDefinition = new TaskDefinition();
		taskDefinition.setCronExpression(generateCronExpression("1", "*", "*", "*", "*", "*"));
		SubTaskDefinition subTaskDefinition = new SubTaskDefinition();
		subTaskDefinition.setActionType("setNextIdeaSubmission");
		subTaskDefinition.setInterval(1L);
		taskDefinition.setSubTask(subTaskDefinition);
		taskDefinitionBean.setTaskDefinition(taskDefinition);
		taskSchedulingService.scheduleATask(UuidGenerator.GENERATOR_NAME, taskDefinitionBean,
				taskDefinition.getCronExpression());
	}

	@GetMapping(path = "/remove/{jobid}")
	public void removeJob(@PathVariable String jobid) {
		taskSchedulingService.removeScheduledTask();
	}
}
