package com.example.IncedoHackathon.Controllers;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.IncedoHackathon.Entities.Schedule;
import com.example.IncedoHackathon.Repositories.ScheduleRepository;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/scheduler")
public class ScheduleController {

	@Autowired
	private ScheduleRepository scheduleRepository;

	public Long convertMinutesToMilliSeconds(Long minutes) {
		return 60 * 1000 * minutes;
	}

	@PostMapping("/startHackathon")
	public void startHackathon() {
		List<Schedule> list = new ArrayList<>();
		Schedule teamRegistrationSchedule = new Schedule(LocalDateTime.now(),
				new Timestamp(Timestamp.valueOf(LocalDateTime.now()).getTime() + convertMinutesToMilliSeconds(5L))
						.toLocalDateTime(),
				"TeamRegistration");
		Schedule ideaSubmissionSchedule = new Schedule(teamRegistrationSchedule.getEndTime(), new Timestamp(
				Timestamp.valueOf(teamRegistrationSchedule.getEndTime()).getTime() + convertMinutesToMilliSeconds(5L))
				.toLocalDateTime(), "IdeaSubmission");
//		Schedule projectEvaluationSchedule = new Schedule(ideaSubmissionSchedule.getEndTime(), new Timestamp(
//				Timestamp.valueOf(ideaSubmissionSchedule.getEndTime()).getTime() + convertMinutesToMilliSeconds(5L))
//				.toLocalDateTime(), "ProjectEvaluation", 0);

		list.add(teamRegistrationSchedule);
		list.add(ideaSubmissionSchedule);
//		list.add(projectEvaluationSchedule);

		scheduleRepository.saveAll(list);
	}

	@GetMapping("/getProjectEvaluationSchedule")
	public ResponseEntity<Object> getDetails() {
		return new ResponseEntity<Object>(scheduleRepository.findByEvent("ProjectEvaluation"), HttpStatus.OK);
	}
}
