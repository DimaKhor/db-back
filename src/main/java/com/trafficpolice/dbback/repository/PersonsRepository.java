package com.trafficpolice.dbback.repository;

import com.trafficpolice.dbback.entity.Persons;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonsRepository extends JpaRepository<Persons, Integer> {
}
