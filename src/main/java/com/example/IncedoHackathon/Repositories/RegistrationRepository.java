package com.example.IncedoHackathon.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.IncedoHackathon.Entities.Registration;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {

	Registration findByTeamName(String teamName);
	Optional<Registration> findByTeamNameAndPassword(String  teamName, String password);

}
