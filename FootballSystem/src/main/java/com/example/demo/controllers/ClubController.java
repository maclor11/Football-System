package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.services.ClubService;

@RestController
public class ClubController {
	private ClubService clubService;
	
	@Autowired
	public ClubController(ClubService clubService) {
		this.clubService = clubService;
	}
}
