package com.trafficpolice.dbback.repository;

import com.trafficpolice.dbback.entity.HijackingsResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HijackingsResultRepository extends JpaRepository<HijackingsResult, Integer> {
}
