package com.example.IncedoHackathon.Controllers;



import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.IncedoHackathon.Entities.IdeaSubmission;
import com.example.IncedoHackathon.Entities.Panelist;
import com.example.IncedoHackathon.Repositories.IdeaSubmissionRepository;
import com.example.IncedoHackathon.Repositories.PanelistRepository;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/panelist")
public class PanelistController {
	
	@Autowired
	private PanelistRepository panelistRepository;
	
	@Autowired
	private IdeaSubmissionRepository ideaSubmissionRepository;
	
	 @GetMapping("/{userName}")
	    public ResponseEntity<Object> getIdeas(@PathVariable("userName") String userName) throws Exception {

	        Optional<Panelist> panelistOptional = panelistRepository.getByUserName(userName);
	        if (panelistOptional.isPresent()) {
	        	Panelist panelist = panelistOptional.get();
	        	List<IdeaSubmission> ideas = ideaSubmissionRepository.findByPanelistAssign(panelist);
	        	return new ResponseEntity<Object>(ideas, HttpStatus.OK);
	        } else {
	            throw new Exception("not assigned any idea");
	        }
	    }

}
