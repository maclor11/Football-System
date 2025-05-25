package com.example.demo.creationDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class PlayerCreationDto {
	
	private Long id;
	
	@NotBlank(message = "Imię nie może być puste")
	private String firstName;
	
	@NotBlank(message = "Nazwisko nie może być puste")
	private String lastName;
	
	@NotNull(message = "Pozycja nie może być pusta")
	private Character position;
	
	@NotNull(message = "Lepsza noga nie może być pusta")
	private Character betterFoot;
	
	@NotNull(message = "Liczba goli nie może być pusta")
	@PositiveOrZero(message = "Liczba goli nie może być ujemna")
	private Integer goals;
	
	@NotNull(message = "Liczba asyst nie może być pusta")
	@PositiveOrZero(message = "Liczba asyst nie może być ujemna")
	private Integer assists;
	
	@NotNull(message = "ID klubu nie może być puste")
	private Long club_id;

	public Long getClub_id() {
		return club_id;
	}

	public void setClub_id(Long club_id) {
		this.club_id = club_id;
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
}
