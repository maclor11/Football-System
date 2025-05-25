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

import com.example.demo.Dto.ClubDto;
import com.example.demo.Dto.GoalDto;
import com.example.demo.Dto.PlayerDto;
import com.example.demo.creationDto.PlayerCreationDto;
import com.example.demo.model.Player;
import com.example.demo.services.ClubService;
import com.example.demo.services.MatchService;
import com.example.demo.services.PlayerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/player")
@Validated
public class PlayerController {

    private final PlayerService playerService;
    private final ClubService clubService;

    @Autowired
    public PlayerController(PlayerService playerService, ClubService clubService) {
        this.playerService = playerService;
        this.clubService = clubService;
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
    public ResponseEntity<?> findPlayerById(@PathVariable Long id) {
        if (id == null || id <= 0) {
            return ResponseEntity.badRequest()
                    .body("Nieprawidłowe ID zawodnika. ID musi być liczbą większą od zera.");
        }
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
    public ResponseEntity<?> deletePlayerById(@PathVariable Long id) {
        if (id == null || id <= 0) {
            return ResponseEntity.badRequest()
                    .body("Nieprawidłowe ID zawodnika. ID musi być liczbą większą od zera.");
        }
        try {
        	PlayerDto player = playerService.findPlayerById(id);
            if (player == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Klub o ID " + id + " nie został znaleziony.");
            }
            playerService.deletePlayerById(id);
            return ResponseEntity.status(HttpStatus.OK)
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
    public ResponseEntity<?> getClubForPlayer(@PathVariable Long id) {
        if (id == null || id <= 0) {
            return ResponseEntity.badRequest()
                    .body("Nieprawidłowe ID zawodnika. ID musi być liczbą większą od zera.");
        }
        try {
            PlayerDto player = playerService.findPlayerById(id);
            if (player == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Nie znaleziono zawodnika o ID " + id + ".");
            }
            return ResponseEntity.ok(player);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body("Nieprawidłowe ID zawodnika: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Błąd podczas pobierania klubu dla zawodnika o ID " + id + ": " + e.getMessage());
        }
    }

    @PutMapping(path = "/{playerId}/club/{clubId}")
    public ResponseEntity<?> setClubForPlayer(@PathVariable Long playerId,
            @PathVariable Long clubId) {
        if (playerId == null || playerId <= 0) {
            return ResponseEntity.badRequest()
                    .body("Nieprawidłowe ID zawodnika. ID musi być liczbą większą od zera.");
        }
        if (clubId == null || clubId <= 0) {
            return ResponseEntity.badRequest()
                    .body("Nieprawidłowe ID klubu. ID musi być liczbą większą od zera.");
        }
        try {
            PlayerDto player = playerService.findPlayerById(playerId);
            if (player == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Nie znaleziono zawodnika o ID " + playerId + ".");
            }
        	ClubDto club = clubService.findClubById(clubId);
            if (club == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Klub o ID " + clubId + " nie został znaleziony.");
            }
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
        try {
        	ClubDto club = clubService.findClubById(playerCreationDto.getClub_id());
            if (club == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Klub o ID " + playerCreationDto.getClub_id() + " nie został znaleziony.");
            }
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
    public ResponseEntity<?> updatePlayer(@PathVariable Long id,
            @RequestBody @Valid PlayerCreationDto playerCreationDto) {
        if (id == null || id <= 0) {
            return ResponseEntity.badRequest()
                    .body("Nieprawidłowe ID zawodnika. ID musi być liczbą większą od zera.");
        }
        try {
        	ClubDto club = clubService.findClubById(playerCreationDto.getClub_id());
            if (club == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Klub o ID " + playerCreationDto.getClub_id() + " nie został znaleziony.");
            }
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
    
    @GetMapping(path = "/{id}/goals")
    public ResponseEntity<?> getGoalsForPlayer(@PathVariable Long id) {
        if (id == null || id <= 0) {
            return ResponseEntity.badRequest()
                    .body("Nieprawidłowe ID gracza. ID musi być liczbą większą od zera.");
        }
        try {
            CollectionModel<GoalDto> goals = playerService.findGoalsForScorer(id);
            if (goals == null || !goals.getContent().iterator().hasNext()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Nie znaleziono goli dla gracza o ID " + id + ".");
            }
            return ResponseEntity.ok(goals);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body("Nieprawidłowe ID gracza: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Błąd podczas pobierania goli dla gracza o ID " + id + ": " + e.getMessage());
        }
    }
    
    @GetMapping(path = "/{id}/assists")
    public ResponseEntity<?> getAssistsForPlayer(@PathVariable Long id) {
        if (id == null || id <= 0) {
            return ResponseEntity.badRequest()
                    .body("Nieprawidłowe ID gracza. ID musi być liczbą większą od zera.");
        }
        try {
            CollectionModel<GoalDto> goals = playerService.findAssistsForPlayer(id);
            if (goals == null || !goals.getContent().iterator().hasNext()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Nie znaleziono asyst dla gracza o ID " + id + ".");
            }
            return ResponseEntity.ok(goals);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body("Nieprawidłowe ID gracza: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Błąd podczas pobierania asyst dla gracza o ID " + id + ": " + e.getMessage());
        }
    }
    
    
}