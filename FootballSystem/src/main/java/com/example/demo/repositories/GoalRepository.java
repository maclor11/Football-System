package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Goal;
import com.example.demo.model.Player;

public interface GoalRepository extends CrudRepository<Goal,Long>{
	List<Goal> findByScorer(Player player);
	List<Goal> findByAssistant(Player player);
}
