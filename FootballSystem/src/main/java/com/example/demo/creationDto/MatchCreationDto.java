package com.example.demo.creationDto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


public class MatchCreationDto {
	
	private Long id;
	
	@NotNull(message = "Numer rundy jest wymagany")
	@Positive(message = "Numer rundy musi być liczbą nieujemną")
    private Integer roundNumber;
	
	@NotNull(message = "ID gospodarza jest wymagane")
    private Long hostId;
	
	@NotNull(message = "ID gościa jest wymagane")
    private Long guestId;
	
	@NotNull(message = "Data jest wymagana")
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
