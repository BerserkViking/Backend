package com.example.IncedoHackathon.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.IncedoHackathon.Repositories.RegistrationRepository;
import com.example.IncedoHackathon.Entities.Registration;

@RestController
@RequestMapping("/api/register")
@CrossOrigin("*")
public class RegistrationController {
	
	@Autowired
	private RegistrationRepository registrationRepository;
	
	@PostMapping("/")
	public void register(@RequestBody Registration user) {
		System.out.println(user);
		registrationRepository.save(user);
	}
	
	@GetMapping("/")
	public Integer send() {
		return 1;
	}
	

}
