package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Player {
	@Id
	@GeneratedValue
	private Long id;
	private String firstName;
	private String lastName;
	private Character position;
	private Character betterFoot;
	private int goals;
	private int assists;
	private Club club;
}
