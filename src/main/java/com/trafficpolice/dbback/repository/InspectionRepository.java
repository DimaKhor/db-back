package com.trafficpolice.dbback.repository;

import com.trafficpolice.dbback.entity.Inspection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InspectionRepository extends JpaRepository<Inspection, Integer> {
}
