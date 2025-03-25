package com.example.demo.model;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Club {
	@Id
	@GeneratedValue
	private Long id;
	private Long budget; //may be null when not established
	private int points;
	private String coachSurname;
	private Set<Player> players;
}
