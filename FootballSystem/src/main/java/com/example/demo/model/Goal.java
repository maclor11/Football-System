package com.example.demo.model;

import com.example.demo.Dto.GoalDto;

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
	
	@Positive
	@Column(name = "goal_minute", nullable=false)
	private Integer minute;
	
	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "scorer_id")
	private Player scorer;
	
	//may be null in case of penalty
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "assistant_id")
	private Player assistant;
	
	public Goal() {}
    
    public Goal(GoalDto goalDto) {
        super();
        this.match = goalDto.getMatch();
        this.minute = goalDto.getMinute();
        this.scorer = goalDto.getScorer();
        this.assistant = goalDto.getAssistant();
    }
    
    public Goal(Long id, GoalDto goalDto) {
        super();
        this.id = id;
        this.match = goalDto.getMatch();
        this.minute = goalDto.getMinute();
        this.scorer = goalDto.getScorer();
        this.assistant = goalDto.getAssistant();
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
