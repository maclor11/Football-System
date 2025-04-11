package com.example.demo.services;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;

import com.example.demo.Dto.*;

import com.example.demo.model.Club;
import com.example.demo.repositories.ClubRepository;

@Service
public class ClubService {
	private final ClubRepository clubRepository;
	//private final ClubMapper clubMapper;
	
	@Autowired
	public ClubService(ClubRepository clubRepository) {
		this.clubRepository = clubRepository;
	}
	
	public CollectionModel<ClubDto> findAllClubs(){
		List<ClubDto> clubsDTO = StreamSupport.stream(clubRepository.findAll().spliterator(), false)
				.map(ClubDto::new)
				.collect(Collectors.toList());
		return CollectionModel.of(clubsDTO);
	}
	
	public ClubDto findClubById(Long id) {
		Club club = clubRepository.findById(id).orElse(null);
		return new ClubDto(club);
	}
	/*
	 * public void addClub(ClubDto clubDto) { Club club = new Club();
	 * club.setName(clubDto.getName()); club.setBudget(clubDto.getBudget());
	 * club.setCoachSurname(clubDto.getCoachSurname());
	 * club.setGoalBalance(clubDto.getGoalBalance());
	 * club.setPoints(clubDto.getPoints()); clubRepository.save(club); }
	 */
	
	public void deleteClubById(Long id){
		clubRepository.deleteById(id);
	}
	
	/*
	 * public void updateClub(ClubDto clubDto) { Club club = new Club();
	 * club.setId(clubDto.getId()); club.setName(clubDto.getName());
	 * club.setBudget(clubDto.getBudget());
	 * club.setCoachSurname(clubDto.getCoachSurname());
	 * club.setGoalBalance(clubDto.getGoalBalance());
	 * club.setPoints(clubDto.getPoints()); clubRepository.save(club); }
	 */
	
	public CollectionModel<PlayerDto> getPlayersForClub(Long id){
		Club club = clubRepository.findById(id)
				.orElse(null);
		List<PlayerDto> playersDTO = club.getPlayers().stream()
				.map(PlayerDto::new)
				.collect(Collectors.toList());
		return CollectionModel.of(playersDTO);
	}
	
}
