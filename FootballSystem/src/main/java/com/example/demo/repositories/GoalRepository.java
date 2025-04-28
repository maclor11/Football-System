package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Goal;

public interface GoalRepository extends CrudRepository<Goal,Long>{

}
