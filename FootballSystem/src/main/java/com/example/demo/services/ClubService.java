package com.example.demo.services;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;

import com.example.demo.Dto.ClubDto;
import com.example.demo.Dto.PlayerDto;
import com.example.demo.model.Club;
import com.example.demo.repositories.ClubRepository;

@Service
public class ClubService {
	private final ClubRepository clubRepository;
	
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
	
	public void addClub(ClubDto clubDto) {
 		clubRepository.save(new Club(clubDto));
 	}
	
	public void updateClub(Long id, ClubDto clubDto) {
 		clubRepository.save(new Club(id,clubDto));
 	}
	 
	
	public void deleteClubById(Long id){
		clubRepository.deleteById(id);
	}
	

	
	public CollectionModel<PlayerDto> getPlayersForClub(Long id){
		Club club = clubRepository.findById(id)
				.orElse(null);
		List<PlayerDto> playersDTO = club.getPlayers().stream()
				.map(PlayerDto::new)
				.collect(Collectors.toList());
		return CollectionModel.of(playersDTO);
	}
	
}
