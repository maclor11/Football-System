package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dto.GoalDto;
import com.example.demo.Dto.MatchDto;
import com.example.demo.creationDto.MatchCreationDto;
import com.example.demo.services.MatchService;



@RestController
@RequestMapping(path = "/match")
public class MatchController {
	
	private final MatchService matchService;
	
	@Autowired
	public MatchController(MatchService matchService) {
		this.matchService = matchService;
	}
	
	@GetMapping
	public CollectionModel<MatchDto> findAllMatches() {
	     return matchService.findAllMatches();
	}
	    
	@GetMapping(path = "/{id}")
	public MatchDto findMatchById(@PathVariable Long id) {
	      return matchService.findMatchById(id);
	}
	    
	@DeleteMapping(path = "/{id}")
	public void deleteMatchById(@PathVariable Long id) {
	       matchService.deleteMatchById(id);
	}
	    
	    
	@PostMapping
	public void addMatch(@RequestBody MatchCreationDto matchCreationDto) {
	    matchService.addMatch(matchCreationDto);
	}

     @PutMapping(path = "/{id}")
     public void updateMatch(@PathVariable Long id, @RequestBody MatchCreationDto matchCreationDto) {
         matchService.updateMatch(id, matchCreationDto);
     }
	     
	    
	 @GetMapping(path = "/{id}/goals")
	    public CollectionModel<GoalDto> getGoalsForMatch(@PathVariable Long id) {
	        return matchService.getGoalsForMatch(id);
	 } 
	 
	
	@GetMapping(path = "/club/{id}")
	public CollectionModel<MatchDto> getMatchesForClub(@PathVariable Long id){
		return matchService.getMatchesForClub(id);
	}
	 
	@GetMapping(path = "/club/{id}/home")
	public CollectionModel<MatchDto> getHomeMatchesForClub(@PathVariable Long id){
		return matchService.getHomeMatchesForClub(id);
	}
	
	@GetMapping(path = "/club/{id}/away")
	public CollectionModel<MatchDto> getAwayMatchesForClub(@PathVariable Long id){
		return matchService.getAwayMatchesForClub(id);
	}
}
