package com.example.demo.creationDto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class GoalCreationDto {
	
	private Long id;
	
	@NotNull(message = "ID meczu jest wymagane")
	private Long matchId;
	
	@NotNull(message = "Minuta meczu jest wymagana")
	@Min(1)
	@Max(90)
	private Integer minute;
	
	@NotNull(message = "ID strzelca jest wymagane")
	private Long scorerId;
	
	@NotNull(message = "ID asystenta jest wymagane")
	private Long assistantId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Integer getMinute() {
        return minute;
    }

    public void setMinute(Integer minute) {
        this.minute = minute;
    }


	public Long getMatchId() {
		return matchId;
	}


	public void setMatchId(Long matchId) {
		this.matchId = matchId;
	}


	public Long getScorerId() {
		return scorerId;
	}


	public void setScorerId(Long scorerId) {
		this.scorerId = scorerId;
	}


	public Long getAssistantId() {
		return assistantId;
	}


	public void setAssistantId(Long assistantId) {
		this.assistantId = assistantId;
	}

    

}
