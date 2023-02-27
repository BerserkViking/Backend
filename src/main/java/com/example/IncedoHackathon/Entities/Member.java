package com.example.IncedoHackathon.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "member")
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false, unique = true, length = 20)
	private String name;

	@Column(nullable = false, unique = true)
	private String email;

	@ManyToOne
	@JoinColumn(referencedColumnName = "teamName", name = "teamName")
	private Registration registration;

	public Member() {
		super();
	}

	public Member(Long id, String name, String email, Registration registration) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.registration = registration;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Registration getRegistration() {
		return registration;
	}

	public void setRegistration(Registration registration) {
		this.registration = registration;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", name=" + name + ", email=" + email + ", registration=" + registration + "]";
	}

}
