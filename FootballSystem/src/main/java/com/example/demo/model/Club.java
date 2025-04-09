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
import lombok.Getter;
import lombok.Setter;

@Entity
public class Club {
	@Id
	@GeneratedValue
	private Long id; 
	
	@NotBlank
	@Column(name = "name", nullable=false)
	private String name;
	
	@PositiveOrZero
	@Column(name = "budget", nullable=true)
	private Long budget; 
	
	@PositiveOrZero
	@Column(name="points", nullable=false)
	private Integer points;
	
	@Column(name = "goal_balance", nullable=false)
	private Integer goalBalance;
	
	@NotBlank
	@Column(name = "coachSurname", nullable=false)
	private String coachSurname;
	
	
	 @OneToMany(fetch = FetchType.LAZY, mappedBy = "club", cascade =
	 CascadeType.ALL) private Set<Player> players;
	 
	
	public Club() {};

	public Club(@NotBlank String name, @NotBlank @PositiveOrZero Long budget,
			@NotBlank @PositiveOrZero Integer points, @NotBlank Integer goalBalance, @NotBlank String coachSurname,
			@NotNull Set<Player> players) {
		super();
		this.name = name;
		this.budget = budget;
		this.points = points;
		this.goalBalance = goalBalance;
		this.coachSurname = coachSurname;
		this.players = players;
	}
	
	public Club(Long id, @NotBlank String name, @NotBlank @PositiveOrZero Long budget,
			@NotBlank @PositiveOrZero Integer points, @NotBlank Integer goalBalance, @NotBlank String coachSurname,
			@NotNull Set<Player> players) {
		super();
		this.id = id;
		this.name = name;
		this.budget = budget;
		this.points = points;
		this.goalBalance = goalBalance;
		this.coachSurname = coachSurname;
		this.players = players;
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

	
	 public Set<Player> getPlayers() { return players; }
	 
	 public void setPlayers(Set<Player> players) { this.players = players; }
	 
	
	
}
