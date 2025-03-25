package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.services.MatchService;

@RestController
public class MatchController {
	private MatchService matchService;
	
	@Autowired
	public MatchController(MatchService matchService) {
		this.matchService = matchService;
	}

}
