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

import com.example.IncedoHackathon.Entities.Registration;
import com.example.IncedoHackathon.Repositories.RegistrationRepository;

@RestController
@CrossOrigin("*")
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private RegistrationRepository registrationRepository;
	
	@PostMapping("/")
	public ResponseEntity<String> login(@RequestBody Registration registration) {
	    Optional<Registration> userOptional = registrationRepository.findByTeamNameAndPassword( registration.getTeamName(), registration.getPassword());
	    System.out.println(registration);
	    if (userOptional.isPresent()) {
	    return ResponseEntity.ok("Login successful");
	    } else {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
	    }
	}
}