package com.example.demo.services;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;

import com.example.demo.Dto.ClubDto;
import com.example.demo.Dto.GoalDto;
import com.example.demo.model.Goal;
import com.example.demo.repositories.GoalRepository;

@Service
public class GoalService {
    private final GoalRepository goalRepository;

    @Autowired
    public GoalService(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    public void addGoal(GoalDto goalDto) {
        goalRepository.save(new Goal(goalDto));
    }

    public void updateGoal(Long id, GoalDto goalDto) {
        goalRepository.save(new Goal(id, goalDto));
    }

    public void deleteGoalById(Long id) {
        goalRepository.deleteById(id);
    }
}