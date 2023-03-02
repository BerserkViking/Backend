package com.example.IncedoHackathon.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.IncedoHackathon.Entities.JudgeInfo;

public interface JudgeInfoRepository extends JpaRepository<JudgeInfo,Long>{

	Optional<JudgeInfo> findByUserNameAndPassword(String userName, String password);

	JudgeInfo findByUserName(String userName);

}
