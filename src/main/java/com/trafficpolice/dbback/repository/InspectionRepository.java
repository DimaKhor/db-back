package com.trafficpolice.dbback.repository;

import com.trafficpolice.dbback.entity.Inspection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InspectionRepository extends JpaRepository<Inspection, Integer> {
    @Query(value = "WITH INSPECT AS (SELECT transport_id, (now() < last_time + periodicity_in_months * INTERVAL '1 month') AS inspection_succeed FROM Inspection) " +
            "SELECT transport_id FROM INSPECT WHERE inspection_succeed = false", nativeQuery = true)
    List<Integer> findTransportIdsWithFailedInspection();
}
