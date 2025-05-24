package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.creationDto.GoalCreationDto;
import com.example.demo.model.Goal;
import com.example.demo.model.Match;
import com.example.demo.model.Player;
import com.example.demo.repositories.GoalRepository;
import com.example.demo.repositories.MatchRepository;
import com.example.demo.repositories.PlayerRepository;

@Service
public class GoalService {
    private final GoalRepository goalRepository;
	private final MatchRepository matchRepository;
	private final PlayerRepository playerRepository;

    @Autowired
    public GoalService(GoalRepository goalRepository, MatchRepository matchRepository, PlayerRepository playerRepository) {
        this.goalRepository = goalRepository;
        this.matchRepository = matchRepository;
        this.playerRepository = playerRepository;
    }

    public void addGoal(GoalCreationDto goalCreationDto) {
    	Goal goal = new Goal(goalCreationDto);
    	Match match = matchRepository.findById(goalCreationDto.getMatchId()).orElse(null);
    	Player scorer = playerRepository.findById(goalCreationDto.getAssistantId()).orElse(null);
    	Player assistant = playerRepository.findById(goalCreationDto.getAssistantId()).orElse(null);
    	goal.setMatch(match);
    	goal.setAssistant(assistant);
    	goal.setScorer(scorer);
        goalRepository.save(goal);
    }

    public void updateGoal(Long id, GoalCreationDto goalCreationDto) {
    	Goal goal = new Goal(id, goalCreationDto);
    	Match match = matchRepository.findById(goalCreationDto.getMatchId()).orElse(null);
    	Player scorer = playerRepository.findById(goalCreationDto.getAssistantId()).orElse(null);
    	Player assistant = playerRepository.findById(goalCreationDto.getAssistantId()).orElse(null);
    	goal.setMatch(match);
    	goal.setAssistant(assistant);
    	goal.setScorer(scorer);
        goalRepository.save(goal);
    }

    public void deleteGoalById(Long id) {
        goalRepository.deleteById(id);
    }

}