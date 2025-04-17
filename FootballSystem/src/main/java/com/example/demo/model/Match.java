package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.Dto.MatchDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

	 public Match(MatchDto matchDto) {
	     this.roundNumber = matchDto.getRoundNumber();
	     this.host = matchDto.getHost();
	     this.guest = matchDto.getGuest();
	     this.date = matchDto.getDate();
	 }
	    
     public Match(Long id, MatchDto matchDto) {
         this.id = id;
         this.roundNumber = matchDto.getRoundNumber();
         this.host = matchDto.getHost();
         this.guest = matchDto.getGuest();
         this.date = matchDto.getDate();
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
