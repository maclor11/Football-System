package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
public class Goal {
	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "match_id")
	private Match match;
	
	@NotBlank
	@Positive
	@Column(name = "minute", nullable=false)
	private int minute;
	
	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "scorer_id")
	private Player scorer;
	
	//may be null in case of penalty
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "assistant_id")
	private Player assistant;
}
