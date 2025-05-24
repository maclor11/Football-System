package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

@RestController
@RequestMapping(path = "/player")
@Validated
public class PlayerController {

    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public ResponseEntity<?> findAllPlayers() {
        try {
            CollectionModel<PlayerDto> players = playerService.findAllPlayers();
            if (players == null || !players.getContent().iterator().hasNext()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Nie znaleziono żadnych zawodników.");
            }
            return ResponseEntity.ok(players);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Błąd podczas pobierania listy zawodników: " + e.getMessage());
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> findPlayerById(@PathVariable @Min(1) Long id) {
        try {
            PlayerDto player = playerService.findPlayerById(id);
            if (player == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Zawodnik o ID " + id + " nie został znaleziony.");
            }
            return ResponseEntity.ok(player);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body("Nieprawidłowe ID zawodnika: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Błąd podczas pobierania zawodnika o ID " + id + ": " + e.getMessage());
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deletePlayerById(@PathVariable @Min(1) Long id) {
        try {
            playerService.deletePlayerById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body("Zawodnik o ID " + id + " został pomyślnie usunięty.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body("Nieprawidłowe ID zawodnika: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Błąd podczas usuwania zawodnika o ID " + id + ": " + e.getMessage());
        }
    }

    @GetMapping(path = "/{id}/club")
    public ResponseEntity<?> getClubForPlayer(@PathVariable @Min(1) Long id) {
        try {
            Club club = playerService.getClubForPlayer(id);
            if (club == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Nie znaleziono klubu dla zawodnika o ID " + id + ".");
            }
            return ResponseEntity.ok(club);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body("Nieprawidłowe ID zawodnika: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Błąd podczas pobierania klubu dla zawodnika o ID " + id + ": " + e.getMessage());
        }
    }

    @PutMapping(path = "/{playerId}/club/{clubId}")
    public ResponseEntity<?> setClubForPlayer(@PathVariable @Min(1) Long playerId,
            @PathVariable @Min(1) Long clubId) {
        try {
            playerService.setClubForPlayer(playerId, clubId);
            return ResponseEntity.ok()
                    .body("Zawodnik o ID " + playerId + " został przypisany do klubu o ID " + clubId + ".");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body("Nieprawidłowe dane: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Błąd podczas przypisywania zawodnika o ID " + playerId + 
                          " do klubu o ID " + clubId + ": " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> addPlayer(@RequestBody @Valid PlayerCreationDto playerCreationDto) {
        if (playerCreationDto == null) {
            return ResponseEntity.badRequest()
                    .body("Dane zawodnika nie mogą być puste.");
        }
        try {
            playerService.addPlayer(playerCreationDto);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Zawodnik został pomyślnie utworzony.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body("Nieprawidłowe dane zawodnika: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Błąd podczas tworzenia zawodnika: " + e.getMessage());
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updatePlayer(@PathVariable @Min(1) Long id,
            @RequestBody @Valid PlayerCreationDto playerCreationDto) {
        if (playerCreationDto == null) {
            return ResponseEntity.badRequest()
                    .body("Dane zawodnika nie mogą być puste.");
        }
        try {
            playerService.updatePlayer(id, playerCreationDto);
            return ResponseEntity.ok()
                    .body("Zawodnik o ID " + id + " został pomyślnie zaktualizowany.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body("Nieprawidłowe dane zawodnika: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Błąd podczas aktualizacji zawodnika o ID " + id + ": " + e.getMessage());
        }
    }
}