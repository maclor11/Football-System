package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Match;

@Repository
public interface MatchRepository extends CrudRepository<Match, Long> {

}
