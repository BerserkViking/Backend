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

import com.example.IncedoHackathon.Entities.Panelist;
import com.example.IncedoHackathon.Repositories.PanelistRepository;

@RestController
@CrossOrigin("*")
@RequestMapping("/Panelist")
public class PanelistLoginController {
	@Autowired
	private PanelistRepository panelistRepository;
	
	@PostMapping("/")
	public ResponseEntity<String> login(@RequestBody Panelist panelist) {
		Optional<Panelist> panelistOptional = panelistRepository.findByUserNameAndPassword( panelist.getUserName(), panelist.getPassword());
	    System.out.println(panelist);
	    if (panelistOptional.isPresent()) {
	    return ResponseEntity.ok("Login successful");
	    } else {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
	    }
	}

}