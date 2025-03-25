package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.services.PlayerService;

@RestController
public class PlayerController {
	private PlayerService playerService;
	
	@Autowired
	public PlayerController(PlayerService playerService) {
		this.playerService = playerService;
	}

}
