package com.example.IncedoHackathon.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.IncedoHackathon.Entities.IdeaSubmission;
import com.example.IncedoHackathon.Entities.Panelist;


public interface IdeaSubmissionRepository extends JpaRepository<IdeaSubmission,Long> {
	
	IdeaSubmission save(IdeaSubmission idea);
//	IdeaSubmission findAllByStatus(IdeaSubmission);

	List<IdeaSubmission> findByPanelistAssign(Panelist panelist);

	IdeaSubmission findByRegistrationTeamName(String teamName);

}
