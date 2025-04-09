package com.example.demo.mappers;

import org.springframework.stereotype.Component;

import com.example.demo.dto.ClubDTO;
import com.example.demo.model.Club;

@Component
public class ClubMapper {
	
	public ClubDTO toDto(Club club) {
		return new ClubDTO(club.getId(), club.getName(), club.getBudget(), club.getPoints(), club.getGoalBalance(), club.getCoachSurname());
	}
	
	public Club toEntity(ClubDTO clubDTO) {
		Club club = new Club();
		club.setId(clubDTO.getId());
		club.setName(clubDTO.getName());
		club.setBudget(clubDTO.getBudget());
		club.setPoints(clubDTO.getPoints());
		club.setGoalBalance(clubDTO.getGoalBalance());
		club.setCoachSurname(clubDTO.getCoachSurname());
		return club;
	}
}
