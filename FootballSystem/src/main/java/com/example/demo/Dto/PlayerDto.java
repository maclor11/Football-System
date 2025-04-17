package com.example.demo.Dto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.RepresentationModel;

import com.example.demo.controllers.PlayerController;
import com.example.demo.model.Club;
import com.example.demo.model.Player;

public class PlayerDto extends RepresentationModel<PlayerDto>{
	private Long id;
	private String firstName;
	private String lastName;
	private Character position;
	private Character betterFoot;
	private Integer goals;
	private Integer assists;
	private Club club;
	//private Long club_id;

	/*
	 * public Long getClub_id() { return club_id; }
	 * 
	 * 
	 * public void setClub_id(Long club_id) { this.club_id = club_id; }
	 */

	public PlayerDto(Player player) {
		super();
		this.id = player.getId();
		this.firstName = player.getFirstName();
		this.lastName = player.getLastName();
		this.position = player.getPosition();
		this.betterFoot = player.getBetterFoot();
		this.goals = player.getGoals();
		this.assists = player.getAssists();
		this.add(linkTo(methodOn(PlayerController.class)
				.getClubForPlayer(player.getId())).withRel("club"));
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
