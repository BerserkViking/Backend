package com.example.IncedoHackathon.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.IncedoHackathon.Entities.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

	Optional<Schedule> findByEvent(String event);

}
