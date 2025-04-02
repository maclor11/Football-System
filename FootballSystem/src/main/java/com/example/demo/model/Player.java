package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
public class Player {
	@Id
	@GeneratedValue
	private Long id;
	
	@NotBlank
	@Column(name = "firstName", nullable=false, updatable=false)
	private String firstName;
	
	@NotBlank
	@Column(name = "lastName", nullable=false, updatable=false)
	private String lastName;
	
	@NotBlank
	@Column(name = "position", nullable=false, updatable=true)
	private Character position;
	
	@NotBlank
	@Column(name = "betterFoot", nullable=false, updatable=false)
	private Character betterFoot;
	
	@NotBlank
	@PositiveOrZero
	@Column(name = "goals", nullable=false, updatable=true)
	private int goals;
	
	@NotBlank
	@PositiveOrZero
	@Column(name = "assists", nullable=false, updatable=true)
	private int assists;
	
	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "club")
	private Club club;
}
