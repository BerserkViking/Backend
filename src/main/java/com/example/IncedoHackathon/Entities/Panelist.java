package com.example.IncedoHackathon.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name="panelist")

public class Panelist {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	

	@Column(nullable = false, unique = true, length = 20)
	private String userName;

	@Column(nullable = false, unique = true)
	private String password;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Panelist(Long id, String userName, String password) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
	}

	public Panelist() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Panelist [id=" + id + ", userName=" + userName + ", password=" + password + "]";
	}
	
	

}
