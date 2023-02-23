package com.example.IncedoHackathon.Controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.IncedoHackathon.Entities.JudgeInfo;
import com.example.IncedoHackathon.Repositories.JudgeInfoRepository;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/judgeinfo")

public class JudgeInfoLoginController {
	@Autowired
	private JudgeInfoRepository judgeInfoRepository;
	
	@PostMapping("/")
	public ResponseEntity<String> login(@RequestBody JudgeInfo judgeInfo) {
		Optional<JudgeInfo> judgeOptional = judgeInfoRepository.findByUserNameAndPassword( judgeInfo.getUserName(), judgeInfo.getPassword());
	    System.out.println(judgeInfo);
	    if (judgeOptional.isPresent()) {
	    return ResponseEntity.ok("Login successful");
	    } else {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
	    }
	}

}

