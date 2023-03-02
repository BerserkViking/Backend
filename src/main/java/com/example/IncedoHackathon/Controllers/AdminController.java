package com.example.IncedoHackathon.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.IncedoHackathon.Entities.Admin;
import com.example.IncedoHackathon.Entities.IdeaSubmission;
import com.example.IncedoHackathon.Entities.JudgeInfo;
import com.example.IncedoHackathon.Entities.JudgeRatings;
import com.example.IncedoHackathon.Entities.Panelist;
import com.example.IncedoHackathon.Repositories.AdminRepository;
import com.example.IncedoHackathon.Repositories.IdeaSubmissionRepository;
import com.example.IncedoHackathon.Repositories.JudgeInfoRepository;
import com.example.IncedoHackathon.Repositories.PanelistRepository;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/admin")
public class AdminController {

	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private PanelistRepository panelistRepository;
	
	@Autowired
	private JudgeInfoRepository judgeInfoRepository;
	
	@Autowired
	private IdeaSubmissionRepository ideaSubmissionRepository;
	
	@PostMapping("/")
	public void addAdminDetails() {
		Admin admin=new Admin();
		admin.setUsername("admin");
		admin.setPassword("password");
		adminRepository.save(admin);
	}
	@GetMapping("/ShowPartcipantDetails")
	public List<IdeaSubmission> showPartcipantDetails() {
		List<IdeaSubmission> partcipantDetails= ideaSubmissionRepository.findAll();
		return partcipantDetails;
	}
	@DeleteMapping("/deletePartcipantDetails/{teamName}")
	public void deletePartcipantDetails(@PathVariable("teamName") String teamName){
		IdeaSubmission deleteIdeaSubmission = ideaSubmissionRepository.findByRegistrationTeamName(teamName);
		if(deleteIdeaSubmission!=null) {
			ideaSubmissionRepository.delete(deleteIdeaSubmission);
	   }
	}
	@PostMapping("/AddPanelistDetails")
	public void addPanelistDetails(@RequestBody Panelist panelist) {
		Panelist addPanelist=new Panelist();
		addPanelist.setUserName(panelist.getUserName());
		addPanelist.setPassword(panelist.getPassword());
		panelistRepository.save(addPanelist);
	}
	@GetMapping("/ShowPanelistDetails")
	public List<Panelist> showPanelistDetails() {
		List<Panelist> panelistDetails= panelistRepository.findAll();
		return panelistDetails;
	}
	@PutMapping("/UpdatePanelistDetails")
	public void updatePanelistDetails(@RequestBody Panelist panelist) {
		Panelist updatePanelist=panelistRepository.findByUserName(panelist.getUserName());
		if(updatePanelist!=null) {
		updatePanelist.setUserName(panelist.getUserName());
		updatePanelist.setPassword(panelist.getPassword());
		panelistRepository.save(updatePanelist);
	}
	}
	@DeleteMapping("/deletePanelistDetails/{userName}")
	public void deletePanelistDetails(@PathVariable("userName") String userName){
		Panelist deletePanelist = panelistRepository.findByUserName(userName);
		if(deletePanelist!=null) {
			panelistRepository.delete(deletePanelist);
	   }
	}
	@PostMapping("/AddJudgeDetails")
	public void addJudgeDetails(@RequestBody JudgeInfo judgeInfo) {
		JudgeInfo addJudgeInfo=new JudgeInfo();
		addJudgeInfo.setUserName(judgeInfo.getUserName());
		addJudgeInfo.setPassword(judgeInfo.getPassword());
		judgeInfoRepository.save(addJudgeInfo);
	}
	@GetMapping("/ShowJudgeDetails")
	public List<JudgeInfo> showJudgeDetails() {
		List<JudgeInfo> judgeDetails= judgeInfoRepository.findAll();
		return judgeDetails;
	}
	@PutMapping("/UpdateJudgeDetails")
	public void updateJudgeDetails(@RequestBody JudgeInfo judgeInfo) {
		JudgeInfo updateJudgeInfo=judgeInfoRepository.findByUserName(judgeInfo.getUserName());
		if(updateJudgeInfo!=null) {
		updateJudgeInfo.setUserName(judgeInfo.getUserName());
		updateJudgeInfo.setPassword(judgeInfo.getPassword());
		judgeInfoRepository.save(updateJudgeInfo);
	}
	}
	@DeleteMapping("/deleteJudgeDetails/{userName}")
	public void deleteJudgeDetails(@PathVariable("userName") String userName){
		JudgeInfo deleteJudgeInfo = judgeInfoRepository.findByUserName(userName);
		if(deleteJudgeInfo!=null) {
			judgeInfoRepository.delete(deleteJudgeInfo);
	   }
	}	
}
