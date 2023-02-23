package com.example.IncedoHackathon.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.IncedoHackathon.Repositories.MemberRepository;
import com.example.IncedoHackathon.Repositories.RegistrationRepository;
import com.example.IncedoHackathon.dto.MemeberRegistrationDTO;
import com.example.IncedoHackathon.Entities.IdeaSubmission;
import com.example.IncedoHackathon.Entities.Member;
import com.example.IncedoHackathon.Entities.Panelist;

@RestController
@RequestMapping("/api/members")
@CrossOrigin("*")
public class MemberController {
	@Autowired
	MemberRepository memberRepository;
	@Autowired
	RegistrationRepository registrationRepository;


    @PostMapping("/")
	public ResponseEntity<Object> registerWithTeam(@RequestBody MemeberRegistrationDTO mrDTO) {
		String teamName = mrDTO.getTeamName();	
		List<Member> members = mrDTO.getMembers();
		members.stream().forEach((member) -> member.setRegistration(registrationRepository.findByTeamName(teamName)));

		return new ResponseEntity<Object>(memberRepository.saveAll(members), HttpStatus.CREATED);
	}
    @GetMapping("/teamName/{teamName}")
	public List<Member> getMembers(@PathVariable("teamName") String teamName) {
		List<Member> list = memberRepository.findByRegistrationTeamName(teamName);
		list.stream().forEach((member) -> {
			member.getRegistration().setPassword(null);
		});

		return list;
	}
}