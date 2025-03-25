package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Goal {
	@Id
	@GeneratedValue
	private Long id;
	private Match match;
	private int minute;
	private Player scorer;
	private Player assistant;
}
