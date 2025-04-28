package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Club;
import com.example.demo.model.Match;

@Repository
public interface MatchRepository extends CrudRepository<Match, Long> {
	List<Match> findByHost(Club club);
	List<Match> findByGuest(Club club);
}
