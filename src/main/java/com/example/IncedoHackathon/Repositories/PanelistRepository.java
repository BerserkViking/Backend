package com.example.IncedoHackathon.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.IncedoHackathon.Entities.Panelist;

public interface PanelistRepository extends JpaRepository<Panelist,Long> {

	Panelist findByUserName(String firstKey);

	Optional<Panelist> findByUserNameAndPassword(String userName, String password);
	Optional<Panelist> getByUserName(String userName);	
}
