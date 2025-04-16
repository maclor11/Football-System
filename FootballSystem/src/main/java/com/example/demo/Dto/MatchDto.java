package com.example.demo.Dto;

import java.time.LocalDateTime;

import org.springframework.hateoas.CollectionModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo; 
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import com.example.demo.model.Club;
import com.example.demo.controllers.MatchController;
import com.example.demo.model.Match;

public class MatchDto extends RepresentationModel<MatchDto>{
    private Long id;
    private Integer roundNumber;
    private Club host;
    private Club guest;
    private LocalDateTime date;

    public MatchDto() {}
    
    public MatchDto(Match match) {
        super();
        this.id = match.getId();
        this.roundNumber = match.getRoundNumber();
        this.host = match.getHost();
        this.guest = match.getGuest();
        this.date = match.getDate();  
        this.add(linkTo(methodOn(MatchController.class)
               .getGoalsForMatch(match.getId())).withRel("goals"));
    }
   
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

    public Club getHost() {
        return host;
    }

    public void setHost(Club host) {
        this.host = host;
    }

    public Club getGuest() {
        return guest;
    }

    public void setGuest(Club guest) {
        this.guest = guest;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}