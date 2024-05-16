package com.trafficpolice.dbback.repository;

import com.trafficpolice.dbback.entity.Colors;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorsRepository extends JpaRepository<Colors, Integer> {
}