package com.example.demo.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Match {
	@Id
	@GeneratedValue
	private Long id;
	private int roundNumber;
	private Club host;
	private Club quest;
	private LocalDate date;
	private int hostGoals;
	private int questGoals;
	private List<Goal> goals;
}
