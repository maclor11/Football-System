package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Club;

@Repository
public interface ClubRepository extends CrudRepository<Club, Long> {

}
