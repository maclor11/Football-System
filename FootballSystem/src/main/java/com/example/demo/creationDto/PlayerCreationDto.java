package com.example.demo.creationDto;


public class PlayerCreationDto {
	
	private Long id;
	private String firstName;
	private String lastName;
	private Character position;
	private Character betterFoot;
	private Integer goals;
	private Integer assists;
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
