package com.example.demo.model;

import com.example.demo.creationDto.PlayerCreationDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	@Column(name = "position", nullable=false, updatable=true)
	private Character position;
	
	@Column(name = "betterFoot", nullable=false, updatable=false)
	private Character betterFoot;
	
	@PositiveOrZero
	@Column(name = "goals", nullable=false, updatable=true)
	private Integer goals;
	
	@PositiveOrZero
	@Column(name = "assists", nullable=false, updatable=true)
	private Integer assists;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "club_id")
	private Club club = null;
	
	public Player() {
		
	}

	public Player(Long id, PlayerCreationDto playerCreationDto) {
		super();
		this.id = id;
		this.firstName = playerCreationDto.getFirstName();
		this.lastName = playerCreationDto.getLastName();
		this.position = playerCreationDto.getPosition();
		this.betterFoot = playerCreationDto.getBetterFoot();
		this.goals = playerCreationDto.getGoals();
		this.assists = playerCreationDto.getAssists();
	}
	
	public Player(PlayerCreationDto playerCreationDto) {
		super();
		this.firstName = playerCreationDto.getFirstName();
		this.lastName = playerCreationDto.getLastName();
		this.position = playerCreationDto.getPosition();
		this.betterFoot = playerCreationDto.getBetterFoot();
		this.goals = playerCreationDto.getGoals();
		this.assists = playerCreationDto.getAssists();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Character getPosition() {
		return position;
	}

	public void setPosition(Character position) {
		this.position = position;
	}

	public Character getBetterFoot() {
		return betterFoot;
	}

	public void setBetterFoot(Character betterFoot) {
		this.betterFoot = betterFoot;
	}

	public Integer getGoals() {
		return goals;
	}

	public void setGoals(Integer goals) {
		this.goals = goals;
	}

	public Integer getAssists() {
		return assists;
	}

	public void setAssists(Integer assists) {
		this.assists = assists;
	}

	public Club getClub() {
		return club;
	}

	public void setClub(Club club) {
		this.club = club;
	}
	
	
}
