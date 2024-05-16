package com.trafficpolice.dbback.repository;

import com.trafficpolice.dbback.entity.AccidentTypes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccidentTypesRepository extends JpaRepository<AccidentTypes, Integer> {
}
