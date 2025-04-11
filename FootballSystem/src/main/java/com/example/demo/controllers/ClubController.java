package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dto.ClubDto;
import com.example.demo.Dto.PlayerDto;
import com.example.demo.model.Club;
import com.example.demo.services.ClubService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping(path = "/club")
public class ClubController {
	
	private ClubService clubService;
	
	@Autowired
	public ClubController(ClubService clubService) {
		this.clubService = clubService;
	}
	
	@GetMapping
	public CollectionModel<ClubDto> findAllClubs(){
		return clubService.findAllClubs();
	}
	
	/*
	 * @PostMapping public void addClub(@RequestBody ClubDto clubDto) {
	 * clubService.addClub(clubDto); }
	 */
	
	@GetMapping(path = "/{id}")
	public ClubDto findClubById(@PathVariable Long id){
		return clubService.findClubById(id);
	}
	
	@DeleteMapping(path = "/{id}")
	public void deleteClubById(@PathVariable Long id) {
		clubService.deleteClubById(id);
	}
	
	/*
	 * @PutMapping(path = "/{id}") public void updateClub(@PathVariable Long
	 * id, @RequestBody ClubDto clubDto) { clubService.updateClub(clubDto); }
	 */
	
	@GetMapping(path = "/{id}/players")
	public CollectionModel<PlayerDto> getPlayersForClub(@PathVariable Long id){
		return clubService.getPlayersForClub(id);
	}
}
