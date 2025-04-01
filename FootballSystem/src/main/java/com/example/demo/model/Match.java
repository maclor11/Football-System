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

@Entity
public class Match {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "roundNumber", nullable=false, updatable=false)
	private int roundNumber;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "host_id")
	private Club host;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "quest_id")
	private Club quest;
	
	@Column(name = "date", nullable=true)
	private LocalDateTime date;
	
	@Column(name = "hostGoals", nullable=false)
	private int hostGoals;
	
	@Column(name = "questGoals", nullable=false)
	private int questGoals;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "match")
	private List<Goal> goals;
}
