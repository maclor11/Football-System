package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Player;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Long> {

}
