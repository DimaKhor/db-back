package com.trafficpolice.dbback.repository;

import com.trafficpolice.dbback.entity.TransportsDamagedInAccident;
import com.trafficpolice.dbback.entity.TransportsDamagedInAccidentId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransportsDamagedInAccidentRepository extends JpaRepository<TransportsDamagedInAccident, TransportsDamagedInAccidentId> {
}
