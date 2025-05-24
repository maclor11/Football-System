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

import com.example.demo.Dto.GoalDto;
import com.example.demo.Dto.MatchDto;
import com.example.demo.creationDto.MatchCreationDto;
import com.example.demo.services.MatchService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

@RestController
@RequestMapping(path = "/match")
@Validated
public class MatchController {

    private final MatchService matchService;

    @Autowired
    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping
    public ResponseEntity<?> findAllMatches() {
        try {
            CollectionModel<MatchDto> matches = matchService.findAllMatches();
            if (matches == null || !matches.getContent().iterator().hasNext()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Nie znaleziono żadnych meczów.");
            }
            return ResponseEntity.ok(matches);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Błąd podczas pobierania listy meczów: " + e.getMessage());
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> findMatchById(@PathVariable @Min(1) Long id) {
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
    public ResponseEntity<?> deleteMatchById(@PathVariable @Min(1) Long id) {
        try {
            matchService.deleteMatchById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
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
    public ResponseEntity<?> updateMatch(@PathVariable @Min(1) Long id,
            @RequestBody @Valid MatchCreationDto matchCreationDto) {
        if (matchCreationDto == null) {
            return ResponseEntity.badRequest()
                    .body("Dane meczu nie mogą być puste.");
        }
        try {
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
    public ResponseEntity<?> getGoalsForMatch(@PathVariable @Min(1) Long id) {
        try {
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

    @GetMapping(path = "/club/{id}")
    public ResponseEntity<?> getMatchesForClub(@PathVariable @Min(1) Long id) {
        try {
            CollectionModel<MatchDto> matches = matchService.getMatchesForClub(id);
            if (matches == null || !matches.getContent().iterator().hasNext()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Nie znaleziono meczów dla klubu o ID " + id + ".");
            }
            return ResponseEntity.ok(matches);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body("Nieprawidłowe ID klubu: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Błąd podczas pobierania meczów dla klubu o ID " + id + ": " + e.getMessage());
        }
    }

    @GetMapping(path = "/club/{id}/home")
    public ResponseEntity<?> getHomeMatchesForClub(@PathVariable @Min(1) Long id) {
        try {
            CollectionModel<MatchDto> homeMatches = matchService.getHomeMatchesForClub(id);
            if (homeMatches == null || !homeMatches.getContent().iterator().hasNext()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Nie znaleziono meczów domowych dla klubu o ID " + id + ".");
            }
            return ResponseEntity.ok(homeMatches);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body("Nieprawidłowe ID klubu: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Błąd podczas pobierania meczów domowych dla klubu o ID " + id + ": " + e.getMessage());
        }
    }

    @GetMapping(path = "/club/{id}/away")
    public ResponseEntity<?> getAwayMatchesForClub(@PathVariable @Min(1) Long id) {
        try {
            CollectionModel<MatchDto> awayMatches = matchService.getAwayMatchesForClub(id);
            if (awayMatches == null || !awayMatches.getContent().iterator().hasNext()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Nie znaleziono meczów wyjazdowych dla klubu o ID " + id + ".");
            }
            return ResponseEntity.ok(awayMatches);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body("Nieprawidłowe ID klubu: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Błąd podczas pobierania meczów wyjazdowych dla klubu o ID " + id + ": " + e.getMessage());
        }
    }
}