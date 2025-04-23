package com.example.demo.creationDto;


public class GoalCreationDto {
	
	private Long id;
	private Long matchId;
	private Integer minute;
	private Long scorerId;
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
