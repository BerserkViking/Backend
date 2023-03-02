package com.example.IncedoHackathon.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.IncedoHackathon.Entities.JudgeRatings;

public interface JudgeRatingsRepository extends JpaRepository<JudgeRatings,Long>{


	JudgeRatings findByTeamName(String teamName);

}
