package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dto.GoalDto;
import com.example.demo.Dto.MatchDto;
import com.example.demo.Dto.PlayerDto;
import com.example.demo.creationDto.GoalCreationDto;
import com.example.demo.model.Match;
import com.example.demo.model.Player;
import com.example.demo.services.GoalService;
import com.example.demo.services.MatchService;
import com.example.demo.services.PlayerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/goal")
public class GoalController {
	
	private GoalService goalService;
	private MatchService matchService;
	private PlayerService playerService;
	
	@Autowired
	public GoalController(GoalService goalService, MatchService matchService, PlayerService playerService) {
		this.goalService = goalService;
		this.matchService = matchService;
		this.playerService = playerService;
	}
	
    @PostMapping
    public ResponseEntity<?> addGoal(@RequestBody @Valid GoalCreationDto goalCreationDto) {
        if (goalCreationDto == null) {
            return ResponseEntity.badRequest()
                    .body("Dane gola nie mogą być puste.");
        }
        try {
        	MatchDto match = matchService.findMatchById(goalCreationDto.getMatchId());
        	PlayerDto scorer = playerService.findPlayerById(goalCreationDto.getScorerId());
        	PlayerDto assistant = playerService.findPlayerById(goalCreationDto.getAssistantId());
            if (match == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Mecz o ID " + goalCreationDto.getMatchId() + " nie został znaleziony.");
            }
            if (scorer == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Gracz o ID " + goalCreationDto.getScorerId() + " nie został znaleziony.");
            }
            if (assistant == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Gracz o ID " + goalCreationDto.getAssistantId() + " nie został znaleziony.");
            }
            goalService.addGoal(goalCreationDto);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Gol został pomyślnie utworzony.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body("Nieprawidłowe dane gola: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Błąd podczas dodawania/aktualizacji gola " + ": " + e.getMessage());
        }
    }
    
    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateGoal(@PathVariable Long id, 
    		@RequestBody @Valid GoalCreationDto goalCreationDto) {
        if (id == null || id <= 0) {
            return ResponseEntity.badRequest()
                    .body("Nieprawidłowe ID gola. ID musi być liczbą większą od zera.");
        }
        if (goalCreationDto == null) {
            return ResponseEntity.badRequest()
                    .body("Dane gola nie mogą być puste.");
        }
        try {
        	MatchDto match = matchService.findMatchById(goalCreationDto.getMatchId());
        	PlayerDto scorer = playerService.findPlayerById(goalCreationDto.getScorerId());
        	PlayerDto assistant = playerService.findPlayerById(goalCreationDto.getAssistantId());
            if (match == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Mecz o ID " + goalCreationDto.getMatchId() + " nie został znaleziony.");
            }
            if (scorer == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Gracz o ID " + goalCreationDto.getScorerId() + " nie został znaleziony.");
            }
            if (assistant == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Gracz o ID " + goalCreationDto.getAssistantId() + " nie został znaleziony.");
            }
            goalService.updateGoal(id, goalCreationDto);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Gol został pomyślnie zaktualizowany.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body("Nieprawidłowe dane gola: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Błąd podczas dodawania/aktualizacji gola " + ": " + e.getMessage());
        }
    }
    
    @GetMapping(path = "/{id}/match")
    public ResponseEntity<?> getMatchForGoal(@PathVariable Long id) {
        if (id == null || id <= 0) {
            return ResponseEntity.badRequest()
                    .body("Nieprawidłowe ID gola. ID musi być liczbą większą od zera.");
        }
        try {
        	GoalDto goal = goalService.findGoalById(id);
            if (goal == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Gol o ID " + id + " nie został znaleziony.");
            }
        	Match match = goalService.getMatchForGoal(id);
            if (match == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Mecz o ID " + match.getId() + " nie został znaleziony.");
            }
            return ResponseEntity.ok(match);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body("Nieprawidłowe ID gola: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Błąd podczas pobierania meczu dla gola o ID " + id + ": " + e.getMessage());
        }
    }
    
    @GetMapping(path = "/{id}/scorer")
    public ResponseEntity<?> getScorerForGoal(@PathVariable Long id) {
        if (id == null || id <= 0) {
            return ResponseEntity.badRequest()
                    .body("Nieprawidłowe ID gola. ID musi być liczbą większą od zera.");
        }
        try {
        	GoalDto goal = goalService.findGoalById(id);
            if (goal == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Gol o ID " + id + " nie został znaleziony.");
            }
        	Player player = goalService.getScorerForGoal(id);
            if (player == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Gracz o ID " + player.getId() + " nie został znaleziony.");
            }
            return ResponseEntity.ok(player);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body("Nieprawidłowe ID gola: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Błąd podczas pobierania meczu dla gola o ID " + id + ": " + e.getMessage());
        }
    }
    @GetMapping(path = "/{id}/assistant")
    public ResponseEntity<?> getAssistantForGoal(@PathVariable Long id) {
        if (id == null || id <= 0) {
            return ResponseEntity.badRequest()
                    .body("Nieprawidłowe ID gola. ID musi być liczbą większą od zera.");
        }
        try {
        	GoalDto goal = goalService.findGoalById(id);
            if (goal == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Gol o ID " + id + " nie został znaleziony.");
            }
        	Player player = goalService.getAssistantForGoal(id);
            if (player == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Gracz o ID " + player.getId() + " nie został znaleziony.");
            }
            return ResponseEntity.ok(player);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body("Nieprawidłowe ID gola: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Błąd podczas pobierania meczu dla gola o ID " + id + ": " + e.getMessage());
        }
    }
	
}
