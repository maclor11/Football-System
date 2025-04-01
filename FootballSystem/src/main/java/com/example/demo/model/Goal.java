package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Goal {
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "match_id")
	private Match match;
	
	@Column(name = "minute", nullable=false)
	private int minute;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "scorer_id")
	private Player scorer;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "assistant_id")
	private Player assistant;
}
