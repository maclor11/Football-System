package com.example.demo.Dto;

import com.example.demo.model.Goal;
import com.example.demo.model.Match;
import com.example.demo.model.Player;


public class GoalDto {
	private Long id;
	private Match match;
	private Integer minute;
	private Player scorer;
	private Player assistant;
	
    public GoalDto() {}
    
    public GoalDto(Goal goal) {
        super();
        this.id = goal.getId();
        this.match = goal.getMatch();
        this.minute = goal.getMinute();
        this.scorer = goal.getScorer();
        this.assistant = goal.getAssistant();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public Integer getMinute() {
        return minute;
    }

    public void setMinute(Integer minute) {
        this.minute = minute;
    }

    public Player getScorer() {
        return scorer;
    }

    public void setScorer(Player scorer) {
        this.scorer = scorer;
    }

    public Player getAssistant() {
        return assistant;
    }

    public void setAssistant(Player assistant) {
        this.assistant = assistant;
    }
}
