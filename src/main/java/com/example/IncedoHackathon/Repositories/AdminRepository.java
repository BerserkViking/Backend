package com.example.IncedoHackathon.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.IncedoHackathon.Entities.Admin;
import com.example.IncedoHackathon.Entities.IdeaSubmission;

public interface AdminRepository extends JpaRepository<Admin,Long> {

}
