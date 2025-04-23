package com.example.demo.creationDto;

import java.time.LocalDateTime;


public class MatchCreationDto {
	
	private Long id;
    private Integer roundNumber;
    private Long hostId;
    private Long guestId;
    private LocalDateTime date;
   
	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(Integer roundNumber) {
        this.roundNumber = roundNumber;
    }

    public Long getHostId() {
        return hostId;
    }

    public void setHostId(Long hostId) {
        this.hostId = hostId;
    }

    public Long getGuestId() {
        return guestId;
    }

    public void setGuest(Long guestId) {
        this.guestId = guestId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
