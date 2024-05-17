package com.trafficpolice.dbback.repository;

import com.trafficpolice.dbback.entity.TransportNumberDirectory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TransportNumberDirectoryRepository extends JpaRepository<TransportNumberDirectory, Integer> {
    Optional<TransportNumberDirectory> findByNumber(String number);

    List<TransportNumberDirectory> findByIssueDateBetween(Date startDate, Date endDate);

    @Query("SELECT tnd.engineId, tnd.chassisId, tnd.coachbuilderId, " +
            "(CASE " +
            "   WHEN EXISTS (SELECT 1 FROM TransportsDamagedInAccident tda WHERE tda.transportNumberDirectory.id = tnd.id) THEN TRUE " +
            "   ELSE FALSE " +
            "END) AS wasInAccident, " +
            "(CASE " +
            "   WHEN tnd.id IN (:failedInspectionIds) THEN FALSE " +
            "   ELSE TRUE " +
            "END) AS passedInspection " +
            "FROM TransportNumberDirectory tnd " +
            "WHERE tnd.number = :number")
    Object[] getCarProfileByNumber(@Param("number") String number, @Param("failedInspectionIds") List<Integer> failedInspectionIds);




}
