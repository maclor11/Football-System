package com.example.demo.services;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;

import com.example.demo.Dto.PlayerDto;
import com.example.demo.creationDto.PlayerCreationDto;
import com.example.demo.model.Club;
import com.example.demo.model.Player;
import com.example.demo.repositories.ClubRepository;
import com.example.demo.repositories.PlayerRepository;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final ClubRepository clubRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository, ClubRepository clubRepository) {
        this.playerRepository = playerRepository;
        this.clubRepository = clubRepository;
    }

    public CollectionModel<PlayerDto> findAllPlayers() {
        List<PlayerDto> playersDTO = StreamSupport.stream(playerRepository.findAll().spliterator(), false)
                .map(PlayerDto::new)
                .collect(Collectors.toList());
        return CollectionModel.of(playersDTO);
    }

    public PlayerDto findPlayerById(Long id) {
        Player player = playerRepository.findById(id).orElse(null);
        return new PlayerDto(player);
    }

    public void deletePlayerById(Long id) {
        playerRepository.deleteById(id);
    }
    
    public Club getClubForPlayer(Long id) {
    	Player player = playerRepository.findById(id).orElse(null);
    	return player.getClub();
    }
    
    public void setClubForPlayer(Long playerId, Long clubId) {
    	Player player = playerRepository.findById(playerId).orElse(null);
    	Club club = clubRepository.findById(clubId).orElse(null);
    	player.setClub(club);
    	playerRepository.save(player);
    }
    
    public void addPlayer(PlayerCreationDto playerCreationDto) {
    	 Player player = new Player(playerCreationDto);
    	 if(playerCreationDto.getClub_id() instanceof Long) {
	         Club club = clubRepository.findById(playerCreationDto.getClub_id()).orElse(null);
	         player.setClub(club);
    	 }
         playerRepository.save(player);
    }
    
    public void updatePlayer(Long id, PlayerCreationDto playerCreationDto) {
    	Player player = new Player(id, playerCreationDto);
        Club club = clubRepository.findById(playerCreationDto.getClub_id()).orElse(null);
        player.setClub(club);
        playerRepository.save(player);
    }
}