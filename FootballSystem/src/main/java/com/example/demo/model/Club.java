package com.example.demo.model;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
public class Club {
	@Id
	@GeneratedValue
	private Long id; 
	
	@NotBlank
	@PositiveOrZero
	@Column(name = "budget", nullable=true)
	private Long budget; 
	
	@NotBlank
	@PositiveOrZero
	@Column(name="points", nullable=false)
	private int points;
	
	@NotBlank
	@Column(name = "coachSurname", nullable=false)
	private String coachSurname;
	
	@NotNull
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "club", cascade = CascadeType.ALL)
	private Set<Player> players;
}
