package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Player {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "firstName", nullable=false, updatable=false)
	private String firstName;
	
	@Column(name = "lastName", nullable=false, updatable=false)
	private String lastName;
	
	@Column(name = "position", nullable=false, updatable=true)
	private Character position;
	
	@Column(name = "betterFoot", nullable=false, updatable=false)
	private Character betterFoot;
	
	@Column(name = "goals", nullable=false, updatable=true)
	private int goals;
	
	@Column(name = "assists", nullable=false, updatable=true)
	private int assists;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "club")
	private Club club;
}
