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
import com.example.demo.Dto.MatchDto;
import com.example.demo.creationDto.MatchCreationDto;
import com.example.demo.model.Club;
import com.example.demo.services.ClubService;
import com.example.demo.services.MatchService;
import com.example.demo.services.PlayerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/match")
@Validated
public class MatchController {

    private final MatchService matchService;
    private final ClubService clubService;
    private final PlayerService playerService;
    

    @Autowired
    public MatchController(MatchService matchService, ClubService clubService, PlayerService playerService) {
        this.matchService = matchService;
        this.clubService = clubService;
        this.playerService = playerService;
    }

    @GetMapping
    public ResponseEntity<?> findAllMatches() {
        try {
            CollectionModel<MatchDto> matches = matchService.findAllMatches();
            return ResponseEntity.ok(matches);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Błąd podczas pobierania listy meczów: " + e.getMessage());
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> findMatchById(@PathVariable Long id) {
        if (id == null || id <= 0) {
            return ResponseEntity.badRequest()
                    .body("Nieprawidłowe ID meczu. ID musi być liczbą większą od zera.");
        }
        try {
            MatchDto match = matchService.findMatchById(id);
            if (match == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Mecz o ID " + id + " nie został znaleziony.");
            }
            return ResponseEntity.ok(match);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body("Nieprawidłowe ID meczu: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Błąd podczas pobierania meczu o ID " + id + ": " + e.getMessage());
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteMatchById(@PathVariable Long id) {
        if (id == null || id <= 0) {
            return ResponseEntity.badRequest()
                    .body("Nieprawidłowe ID meczu. ID musi być liczbą większą od zera.");
        }
        try {
        	MatchDto match = matchService.findMatchById(id);
            if (match == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Mecz o ID " + id + " nie został znaleziony.");
            }
            matchService.deleteMatchById(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Mecz o ID " + id + " został pomyślnie usunięty.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body("Nieprawidłowe ID meczu: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Błąd podczas usuwania meczu o ID " + id + ": " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> addMatch(@RequestBody @Valid MatchCreationDto matchCreationDto) {
        if (matchCreationDto == null) {
            return ResponseEntity.badRequest()
                    .body("Dane meczu nie mogą być puste.");
        }
        try {
        	ClubDto host = clubService.findClubById(matchCreationDto.getHostId());
        	ClubDto guest = clubService.findClubById(matchCreationDto.getGuestId());
            if (host == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Klub o ID " + matchCreationDto.getHostId() + " nie został znaleziony.");
            }
            if (guest == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Klub o ID " + matchCreationDto.getGuestId() + " nie został znaleziony.");
            }
            if (matchCreationDto.getHostId() == matchCreationDto.getGuestId()) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Ten sam klub nie może być jednocześnie gościem i gospodarzem");
            }
            matchService.addMatch(matchCreationDto);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Mecz został pomyślnie utworzony.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body("Nieprawidłowe dane meczu: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Błąd podczas tworzenia meczu: " + e.getMessage());
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateMatch(@PathVariable Long id,
            @RequestBody @Valid MatchCreationDto matchCreationDto) {
        if (id == null || id <= 0) {
            return ResponseEntity.badRequest()
                    .body("Nieprawidłowe ID meczu. ID musi być liczbą większą od zera.");
        }
        if (matchCreationDto == null) {
            return ResponseEntity.badRequest()
                    .body("Dane meczu nie mogą być puste.");
        }
        try {
        	MatchDto match = matchService.findMatchById(id);
            if (match == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Mecz o ID " + id + " nie został znaleziony.");
            }
        	ClubDto host = clubService.findClubById(matchCreationDto.getHostId());
        	ClubDto guest = clubService.findClubById(matchCreationDto.getGuestId());
            if (host == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Klub o ID " + matchCreationDto.getHostId() + " nie został znaleziony.");
            }
            if (guest == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Klub o ID " + matchCreationDto.getGuestId() + " nie został znaleziony.");
            }
            if (matchCreationDto.getHostId() == matchCreationDto.getGuestId()) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Ten sam klub nie może być jednocześnie gościem i gospodarzem");
            }
            matchService.updateMatch(id, matchCreationDto);
            return ResponseEntity.ok()
                    .body("Mecz o ID " + id + " został pomyślnie zaktualizowany.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body("Nieprawidłowe dane meczu: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Błąd podczas aktualizacji meczu o ID " + id + ": " + e.getMessage());
        }
    }

    @GetMapping(path = "/{id}/goals")
    public ResponseEntity<?> getGoalsForMatch(@PathVariable Long id) {
        if (id == null || id <= 0) {
            return ResponseEntity.badRequest()
                    .body("Nieprawidłowe ID meczu. ID musi być liczbą większą od zera.");
        }
        try {
        	MatchDto match = matchService.findMatchById(id);
            if (match == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Mecz o ID " + id + " nie został znaleziony.");
            }
            CollectionModel<GoalDto> goals = matchService.getGoalsForMatch(id);
            if (goals == null || !goals.getContent().iterator().hasNext()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Nie znaleziono goli dla meczu o ID " + id + ".");
            }
            return ResponseEntity.ok(goals);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body("Nieprawidłowe ID meczu: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Błąd podczas pobierania goli dla meczu o ID " + id + ": " + e.getMessage());
        }
    }
    
    @GetMapping(path = "/{id}/host")
    public ResponseEntity<?> getHostForMatch(@PathVariable Long id) {
        if (id == null || id <= 0) {
            return ResponseEntity.badRequest()
                    .body("Nieprawidłowe ID meczu. ID musi być liczbą większą od zera.");
        }
        try {
        	MatchDto match = matchService.findMatchById(id);
            if (match == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Mecz o ID " + id + " nie został znaleziony.");
            }
        	Club host = matchService.getHostForMatch(id);
            if (host == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Klub o ID " + host.getId() + " nie został znaleziony.");
            }
            return ResponseEntity.ok(host);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body("Nieprawidłowe ID meczu: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Błąd podczas pobierania gospodarza dla meczu o ID " + id + ": " + e.getMessage());
        }
    }
    
    @GetMapping(path = "/{id}/guest")
    public ResponseEntity<?> getGuestForMatch(@PathVariable Long id) {
        if (id == null || id <= 0) {
            return ResponseEntity.badRequest()
                    .body("Nieprawidłowe ID meczu. ID musi być liczbą większą od zera.");
        }
        try {
        	MatchDto match = matchService.findMatchById(id);
            if (match == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Mecz o ID " + id + " nie został znaleziony.");
            }
        	Club guest = matchService.getGuestForMatch(id);
            if (guest == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Klub o ID " + guest.getId() + " nie został znaleziony.");
            }
            return ResponseEntity.ok(guest);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body("Nieprawidłowe ID meczu: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Błąd podczas pobierania gościa dla meczu o ID " + id + ": " + e.getMessage());
        }
    }
    
}