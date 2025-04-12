package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;

import com.example.demo.Dto.PlayerDto;
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
}