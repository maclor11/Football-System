package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dto.ClubDto;
import com.example.demo.Dto.GoalDto;
import com.example.demo.Dto.MatchDto;
import com.example.demo.Dto.PlayerDto;
import com.example.demo.services.ClubService;
import com.example.demo.services.MatchService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

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
	    
	    /*
	     * @PostMapping
	     * public void addMatch(@RequestBody MatchDto matchDto) {
	     *     matchService.addMatch(matchDto);
	     * }
	     */
	    
	    /*
	     * @PutMapping(path = "/{id}")
	     * public void updateMatch(@PathVariable Long id, @RequestBody MatchDto matchDto) {
	     *     matchService.updateMatch(matchDto);
	     * }
	     */
	    
	 @GetMapping(path = "/{id}/goals")
	    public CollectionModel<GoalDto> getGoalsForMatch(@PathVariable Long id) {
	        return matchService.getGoalsForMatch(id);
	 }
}
