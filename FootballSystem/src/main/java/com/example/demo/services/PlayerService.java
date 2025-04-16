package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Dto.PlayerDto;
import com.example.demo.model.Club;
import com.example.demo.model.Player;
import com.example.demo.repositories.PlayerRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
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
    
    public void addPlayer(String firstName, String lastName, Character position, Character betterFoot, Integer goals, Integer assists,  Club club) {
    	Player player = new Player(firstName,lastName,position, betterFoot, goals, assists, club);
    	playerRepository.save(player);
    }
}