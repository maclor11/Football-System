package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.Dto.MatchDto;
import com.example.demo.creationDto.MatchCreationDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.PositiveOrZero;

@Entity 
public class Match {
	@Id
	@GeneratedValue
	private Long id;
	
	@PositiveOrZero
	@Column(name = "roundNumber", nullable=false, updatable=false)
	private Integer roundNumber;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "host_id")
	private Club host;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "guest_id")
	private Club guest;
	
	@Column(name = "date", nullable=true)
	private LocalDateTime date;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "match")
	private List<Goal> goals;
	
	 public Match() {}

	 public Match(MatchCreationDto matchCreationDto) {
	     this.roundNumber = matchCreationDto.getRoundNumber();
	     this.date = matchCreationDto.getDate();
	 }
	    
     public Match(Long id, MatchCreationDto matchCreationDto) {
         this.id = id;
         this.roundNumber = matchCreationDto.getRoundNumber();
         this.date = matchCreationDto.getDate();
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

	 public List<Goal> getGoals() {
	     return goals;
	 }

	 public void setGoals(List<Goal> goals) {
	     this.goals = goals;
	 }	
}
