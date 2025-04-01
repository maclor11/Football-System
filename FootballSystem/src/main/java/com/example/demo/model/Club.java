package com.example.demo.model;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Club {
	@Id
	@GeneratedValue
	private Long id; 
	
	@Column(name = "budget", nullable=true)
	private Long budget; 
	
	@Column(name="points", nullable=false)
	private int points;
	
	@Column(name = "coachSurname", nullable=false)
	private String coachSurname;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "club",
			cascade = CascadeType.ALL)
	private Set<Player> players;
}
