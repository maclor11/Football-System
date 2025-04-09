package com.example.demo.dto;

public class ClubDTO {
	private Long id; 
	private String name;
	private Long budget; 
	private Integer points;
	private Integer goalBalance;
	private String coachSurname;
	
	public ClubDTO(Long id, String name, Long budget, Integer points, Integer goalBalance, String coachSurname) {
		super();
		this.id = id;
		this.name = name;
		this.budget = budget;
		this.points = points;
		this.goalBalance = goalBalance;
		this.coachSurname = coachSurname;
	}
	
	public ClubDTO(String name, Long budget, Integer points, Integer goalBalance, String coachSurname) {
		super();
		this.name = name;
		this.budget = budget;
		this.points = points;
		this.goalBalance = goalBalance;
		this.coachSurname = coachSurname;
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
