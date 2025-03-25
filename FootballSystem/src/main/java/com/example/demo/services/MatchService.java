package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repositories.MatchRepository;

@Service
public class MatchService {
	private final MatchRepository matchRepository;
	
	@Autowired
	public MatchService(MatchRepository matchRepository) {
		this.matchRepository = matchRepository;
	}
}
