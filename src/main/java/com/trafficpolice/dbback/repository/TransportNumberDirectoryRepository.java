package com.trafficpolice.dbback.repository;

import com.trafficpolice.dbback.entity.TransportNumberDirectory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TransportNumberDirectoryRepository extends JpaRepository<TransportNumberDirectory, Integer> {
    Optional<TransportNumberDirectory> findByNumber(String number);

    List<TransportNumberDirectory> findByIssueDateBetween(Date startDate, Date endDate);
}
