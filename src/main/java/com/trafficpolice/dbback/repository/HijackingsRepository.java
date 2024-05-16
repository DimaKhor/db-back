package com.trafficpolice.dbback.repository;

import com.trafficpolice.dbback.entity.Hijackings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HijackingsRepository extends JpaRepository<Hijackings, Integer> {
}
