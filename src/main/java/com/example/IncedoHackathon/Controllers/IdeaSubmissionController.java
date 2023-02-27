package com.example.IncedoHackathon.Controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.IncedoHackathon.Repositories.IdeaSubmissionRepository;
import com.example.IncedoHackathon.Repositories.PanelistRepository;
import com.example.IncedoHackathon.Repositories.RegistrationRepository;
import com.example.IncedoHackathon.dto.IdeaSubmissionDTO;
import com.example.IncedoHackathon.dto.StatusUpdationDTO;
import com.example.IncedoHackathon.Entities.IdeaSubmission;
import com.example.IncedoHackathon.Entities.Panelist;

@RestController
@RequestMapping("/api/idea-submission")
@CrossOrigin("*")
public class IdeaSubmissionController {

	@Autowired
	private IdeaSubmissionRepository ideaSubmissionRepository;

	@Autowired
	private PanelistRepository panelistRepository;

	@Autowired
	private RegistrationRepository registrationRepository;

	@PostMapping("/")
	public IdeaSubmission takeIdea(@RequestBody IdeaSubmissionDTO ideaSubmissionDTO) {
		IdeaSubmission ideaSubmission = ideaSubmissionDTO.getIdeaSubmission();
		ideaSubmission.setRegistration(registrationRepository.findByTeamName(ideaSubmissionDTO.getTeamName()));
		Panelist assignedPanelist = getNextAvailablePanelist();
		ideaSubmission.setPanelistAssign(assignedPanelist);
		return ideaSubmissionRepository.save(ideaSubmission);
	}

	private Panelist getNextAvailablePanelist() {
		List<Panelist> allPanelists = panelistRepository.findAll();
		List<IdeaSubmission> ideas = ideaSubmissionRepository.findAll();
		Map<String, Integer> tm = new HashMap<>();
		allPanelists.stream()
				.forEach((panelist) -> tm.put(panelist.getUserName(), tm.getOrDefault(panelist.getUserName(), 0) + 1));
		ideas.stream().forEach((idea) -> tm.put(idea.getPanelistAssign().getUserName(),
				tm.getOrDefault(idea.getPanelistAssign().getUserName(), 0) + 1));

		int min = Integer.MAX_VALUE;
		String minName = "";
		for (String name : tm.keySet()) {
			if (tm.get(name) < min) {
				min = tm.get(name);
				minName = name;
			}
		}
		Panelist panelist = panelistRepository.findByUserName(minName);
		return panelist;
	}

	@PostMapping("/update-status")
	public ResponseEntity<Object> updateStatus(@RequestBody StatusUpdationDTO suDTO) {
		Optional<IdeaSubmission> ideaOptional = ideaSubmissionRepository.findById(suDTO.getIdeaSubmissionId());
		if (ideaOptional.isPresent()) {
			IdeaSubmission idea = ideaOptional.get();
			idea.setStatus(suDTO.getStatus());
			idea.setFeedback(suDTO.getFeedback());
			return new ResponseEntity<Object>(ideaSubmissionRepository.save(idea), HttpStatus.OK);
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}
//	@GetMapping("/teamName/{teamName}")
//	public List<IdeaSubmission> getIdea(@PathVariable("teamName") String teamName){
//		List<IdeaSubmission> list = ideaSubmissionRepository.findByRegistrationTeamName(teamName);
//		return list;
//	}
//
//}
//	@GetMapping("/teamName/{teamName}")
//	public List<IdeaSubmission> getIdea(@PathVariable("teamName") String teamName){
//		List<IdeaSubmission> list = ideaSubmissionRepository.findByRegistrationTeamName(teamName);
//		list.stream().forEach((member) -> {
//			member.getRegistration().setPassword(null);member.getPanelistAssign().setPassword(null);
//		});
//		return list;
//	}
	@GetMapping("/teamName/{teamName}")
	public IdeaSubmission getIdea(@PathVariable("teamName") String teamName) {
		IdeaSubmission submit = ideaSubmissionRepository.findByRegistrationTeamName(teamName);
		return submit;
	}
	
//	//@CrossOrigin("*")
	@PutMapping("/idea/teamName")
	public ResponseEntity<IdeaSubmission> updateIdeaDetails(@RequestBody IdeaSubmissionDTO ideaSubmissionDTO) {
		
		IdeaSubmission updateIdeaSubmission = ideaSubmissionRepository
				.findByRegistrationTeamName(ideaSubmissionDTO.getTeamName());

		if(updateIdeaSubmission!=null && updateIdeaSubmission.getStatus()=="Revert") {
		updateIdeaSubmission.setProblemStatement(ideaSubmissionDTO.getIdeaSubmission().getProblemStatement());
		updateIdeaSubmission.setProblemSolution(ideaSubmissionDTO.getIdeaSubmission().getProblemSolution());
		updateIdeaSubmission.setStatus(updateIdeaSubmission.getStatus());
		ideaSubmissionRepository.save(updateIdeaSubmission);
		}
		return ResponseEntity.ok(updateIdeaSubmission);
	}
}

