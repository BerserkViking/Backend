package com.example.IncedoHackathon.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.IncedoHackathon.Entities.JudgeRatings;
import com.example.IncedoHackathon.Repositories.JudgeRatingsRepository;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/judge")
public class JudgeRatingsController {
	
	@Autowired
	private JudgeRatingsRepository judgeRepository;
	
//	@PostMapping("/")
//	public void register(@RequestBody JudgeRatings judgeRatings) {
//		judgeRepository.save(judgeRatings);
//	}
	

	@PostMapping("/")
	public void register(@RequestBody JudgeRatings judgeRatings) {
		JudgeRatings updateJudgeRatings=judgeRepository.findByTeamName(judgeRatings.getTeamName());
		if(updateJudgeRatings!=null){
			Long id=updateJudgeRatings.getId();
			judgeRatings.setId(id);
			judgeRatings.setAverage(String.valueOf((Double.parseDouble(updateJudgeRatings.getAverage())+Double.parseDouble(judgeRatings.getAverage()))/2));
			judgeRepository.save(judgeRatings);
		}else {
		judgeRepository.save(judgeRatings);
	}
	}
	

}