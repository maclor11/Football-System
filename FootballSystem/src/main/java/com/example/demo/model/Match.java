package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
public class Match {
	@Id
	@GeneratedValue
	private Long id;
	
	@NotBlank
	@Positive
	@Column(name = "roundNumber", nullable=false, updatable=false)
	private int roundNumber;
	
	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "host_id")
	private Club host;
	
	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "quest_id")
	private Club quest;
	
	@NotBlank
	@Column(name = "date", nullable=true)
	private LocalDateTime date;
	
	@NotBlank
	@PositiveOrZero
	@Column(name = "hostGoals", nullable=false)
	private int hostGoals;
	
	@NotBlank
	@PositiveOrZero
	@Column(name = "questGoals", nullable=false)
	private int questGoals;
	
	@NotNull
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "match")
	private List<Goal> goals;
}
