package com.example.demo.services;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;

import com.example.demo.Dto.GoalDto;
import com.example.demo.Dto.MatchDto;
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
    
    
    public void addMatch(MatchDto matchDto) {
        //matchRepository.save(new Match(matchDto));
    }

    
    public void deleteMatchById(Long id) {
        matchRepository.deleteById(id);
    }
    
    
    public void updateMatch(Long id, MatchDto matchDto) {
        //matchRepository.save(new Match(id,matchDto));
    }
     
    
    public CollectionModel<GoalDto> getGoalsForMatch(Long id) {
        Match match = matchRepository.findById(id)
               .orElse(null);
      List<GoalDto> goalsDTO = match.getGoals().stream()
                .map(GoalDto::new)
                .collect(Collectors.toList());
        return CollectionModel.of(goalsDTO);
    }   
}
