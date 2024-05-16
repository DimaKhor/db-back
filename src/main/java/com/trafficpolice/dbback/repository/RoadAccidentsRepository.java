package com.trafficpolice.dbback.repository;

import com.trafficpolice.dbback.entity.RoadAccidents;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoadAccidentsRepository extends JpaRepository<RoadAccidents, Integer> {
}
