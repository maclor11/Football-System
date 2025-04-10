package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ClubDTO;
import com.example.demo.services.ClubService;

@RestController
public class ClubController {
	
	private ClubService clubService;
	
	@Autowired
	public ClubController(ClubService clubService) {
		this.clubService = clubService;
	}
	
	@GetMapping(path = "/clubs")
	public List<ClubDTO> findAllClubs(){
		return clubService.findAllClubs();
	}
	
	@PostMapping(path = "/addClub")
	public void addClub( @RequestParam String name, @RequestParam Long budget,@RequestParam Integer points,@RequestParam Integer goalBalance,@RequestParam String coachSurname) {
		clubService.addClub(name, budget, points, goalBalance, coachSurname);
	}
	
	@DeleteMapping(path = "/deleteClub")
	public void deleteClubById(@RequestParam Long id) {
		clubService.deleteClubById(id);
	}
	
	@PutMapping(path = "/updateClub")
	public void updateClub(@RequestParam Long id, @RequestParam String name, @RequestParam Long budget,@RequestParam Integer points,@RequestParam Integer goalBalance,@RequestParam String coachSurname) {
		clubService.updateClub(id, name, budget, points, goalBalance, coachSurname);
	}
}
