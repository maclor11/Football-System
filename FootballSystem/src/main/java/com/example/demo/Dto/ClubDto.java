package com.example.demo.Dto;

import org.springframework.hateoas.RepresentationModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo; 
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.example.demo.controllers.ClubController;
import com.example.demo.model.Club;

public class ClubDto extends RepresentationModel<ClubDto>{
	private Long id; 
	private String name;
	private Long budget; 
	private Integer points;
	private Integer goalBalance;
	private String coachSurname;
	
	public ClubDto() {}
	
	public ClubDto(Club club) {
		super();
		this.id = club.getId();
		this.name = club.getName();
		this.budget = club.getBudget();
		this.points = club.getPoints();
		this.goalBalance = club.getGoalBalance();
		this.coachSurname = club.getCoachSurname();
		this.add(linkTo(methodOn(ClubController.class)
				.getPlayersForClub(club.getId())).withRel("players"));
	}
	
	
	
	 public Long getId() { return id; } public void setId(Long id) { this.id = id;
	 }
	 
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getBudget() {
		return budget;
	}
	public void setBudget(Long budget) {
		this.budget = budget;
	}
	public Integer getPoints() {
		return points;
	}
	public void setPoints(Integer points) {
		this.points = points;
	}
	public Integer getGoalBalance() {
		return goalBalance;
	}
	public void setGoalBalance(Integer goalBalance) {
		this.goalBalance = goalBalance;
	}
	public String getCoachSurname() {
		return coachSurname;
	}
	public void setCoachSurname(String coachSurname) {
		this.coachSurname = coachSurname;
	}
	
	
}
