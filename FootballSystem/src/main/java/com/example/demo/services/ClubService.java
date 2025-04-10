package com.example.demo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ClubDTO;
import com.example.demo.mappers.ClubMapper;
import com.example.demo.model.Club;
import com.example.demo.repositories.ClubRepository;

@Service
public class ClubService {
	private final ClubRepository clubRepository;
	private final ClubMapper clubMapper;
	
	@Autowired
	public ClubService(ClubRepository clubRepository, ClubMapper clubMapper) {
		this.clubRepository = clubRepository;
		this.clubMapper = clubMapper;
	}
	
	public List<ClubDTO> findAllClubs(){
		List<Club> clubs = (List<Club>) clubRepository.findAll();
		return clubs.stream().map(clubMapper::toDto).collect(Collectors.toList());
	}
	
	public void addClub(String name, Long budget, Integer points, Integer goalBalance, String coachSurname) {
		ClubDTO clubDTO = new ClubDTO(name,budget, points,goalBalance,coachSurname);
		clubRepository.save(clubMapper.toEntity(clubDTO));
	}
	
	public void deleteClubById(Long id){
		clubRepository.deleteById(id);
	}
	
	public void updateClub(Long id, String name, Long budget, Integer points, Integer goalBalance, String coachSurname) {
		ClubDTO clubDTO = new ClubDTO(id,name,budget, points,goalBalance,coachSurname);
		clubRepository.save(clubMapper.toEntity(clubDTO));
	}
	
}
