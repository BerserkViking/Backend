package com.example.IncedoHackathon.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.IncedoHackathon.Entities.Member;

public interface MemberRepository extends JpaRepository<Member,Long> {

	List<Member> findByRegistrationTeamName(String teamName);
	
}