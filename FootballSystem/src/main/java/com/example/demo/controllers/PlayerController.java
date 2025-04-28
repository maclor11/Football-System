package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dto.PlayerDto;
import com.example.demo.creationDto.PlayerCreationDto;
import com.example.demo.model.Club;
import com.example.demo.services.PlayerService;

@RestController
@RequestMapping(path = "/player")
public class PlayerController {
    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public CollectionModel<PlayerDto> findAllPlayers() {
        return playerService.findAllPlayers();
    }

    @GetMapping(path = "/{id}")
    public PlayerDto findPlayerById(@PathVariable Long id) {
        return playerService.findPlayerById(id);
    }

    @DeleteMapping(path = "/{id}")
    public void deletePlayerById(@PathVariable Long id) {
        playerService.deletePlayerById(id);
    }
    
    @GetMapping(path = "/{id}/club")
    public Club getClubForPlayer(@PathVariable Long id) {
    	return playerService.getClubForPlayer(id);
    }
    
    @PutMapping(path = "/{playerId}/club/{clubId}")
    public void setClubForPlayer(@PathVariable Long playerId, @PathVariable Long clubId) {
    	playerService.setClubForPlayer(playerId, clubId);
    }
    
    @PostMapping
    public void addPlayer(@RequestBody PlayerCreationDto playerCreationDto) {
    	playerService.addPlayer(playerCreationDto);
    }
    
    @PutMapping(path = "/{id}")
    public void updatePlayer(@PathVariable Long id, @RequestBody PlayerCreationDto playerCreationDto) {
    	playerService.updatePlayer(id, playerCreationDto);
    }
    

}
