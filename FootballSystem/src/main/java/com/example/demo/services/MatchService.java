package com.example.demo.services;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;

import com.example.demo.Dto.GoalDto;
import com.example.demo.Dto.MatchDto;
import com.example.demo.creationDto.MatchCreationDto;
import com.example.demo.model.Club;
import com.example.demo.model.Match;
import com.example.demo.repositories.ClubRepository;
import com.example.demo.repositories.MatchRepository;

@Service
public class MatchService {
	private final MatchRepository matchRepository;
	private final ClubRepository clubRepository;
	
    @Autowired
    public MatchService(MatchRepository matchRepository, ClubRepository clubRepository) {
        this.matchRepository = matchRepository;
        this.clubRepository = clubRepository;
    }
    
    public CollectionModel<MatchDto> findAllMatches() {
        List<MatchDto> matchesDTO = StreamSupport.stream(matchRepository.findAll().spliterator(), false)
                .map(MatchDto::new)
                .collect(Collectors.toList());
        return CollectionModel.of(matchesDTO);
    }
    
    public MatchDto findMatchById(Long id) {
        Match match = matchRepository.findById(id).orElse(null);
        return new MatchDto(match);
    }
    
    
    public void addMatch(MatchCreationDto matchCreationDto) {
        Match match = new Match(matchCreationDto);
        Club host = clubRepository.findById(matchCreationDto.getHostId()).orElse(null);
        Club guest = clubRepository.findById(matchCreationDto.getGuestId()).orElse(null);
        match.setHost(host);
        match.setGuest(guest);
        matchRepository.save(match);
    }

    
    public void deleteMatchById(Long id) {
        matchRepository.deleteById(id);
    }
    
    
    public void updateMatch(Long id, MatchCreationDto matchCreationDto) {
    	Match match = new Match(id, matchCreationDto);
        Club host = clubRepository.findById(matchCreationDto.getHostId()).orElse(null);
        Club guest = clubRepository.findById(matchCreationDto.getGuestId()).orElse(null);
        match.setHost(host);
        match.setGuest(guest);
        matchRepository.save(match);
    }
     
    
    public CollectionModel<GoalDto> getGoalsForMatch(Long id) {
        Match match = matchRepository.findById(id)
               .orElse(null);
      List<GoalDto> goalsDTO = match.getGoals().stream()
                .map(GoalDto::new)
                .collect(Collectors.toList());
        return CollectionModel.of(goalsDTO);
    }   
    
    public CollectionModel<MatchDto> getMatchesForClub(Long id) {
    	Club club = clubRepository.findById(id).orElse(null);
    	List<MatchDto> m1 = matchRepository.findByGuest(club).stream()
                .map(MatchDto::new) 
                .collect(Collectors.toList());  
    	List<MatchDto> m2 = matchRepository.findByHost(club).stream()
                .map(MatchDto::new) 
                .collect(Collectors.toList());  
    	m1.addAll(m2);
    	return CollectionModel.of(m1);
    }
}
