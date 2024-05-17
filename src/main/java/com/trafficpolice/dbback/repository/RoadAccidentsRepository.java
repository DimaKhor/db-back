package com.trafficpolice.dbback.repository;

import com.trafficpolice.dbback.entity.RoadAccidents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoadAccidentsRepository extends JpaRepository<RoadAccidents, Integer> {

    @Query(value = "WITH accident AS (SELECT type_id, COUNT(type_id) as count FROM RoadAccidents " +
            "WHERE date BETWEEN CAST(:startDate AS timestamp) AND CAST(:endDate AS timestamp) " +
            "GROUP BY type_id) " +
            "SELECT AccidentTypes.name, COALESCE(count, 0) FROM AccidentTypes " +
            "LEFT JOIN accident ON AccidentTypes.id = type_id " +
            "WHERE AccidentTypes.name = :typeName", nativeQuery = true)
    List<Object[]> getAccidentStatisticsByTypeAndPeriod(String typeName, String startDate, String endDate);

    @Query(value = "SELECT place AS most_dangerous_place FROM roadaccidents " +
            "GROUP BY place " +
            "HAVING COUNT(place) = (SELECT COUNT(place) FROM roadaccidents " +
            "GROUP BY place ORDER BY COUNT(place) DESC LIMIT 1)", nativeQuery = true)
    List<String> findMostDangerousPlaces();

    @Query(value = "SELECT reason as most_popular_reason FROM roadaccidents " +
            "GROUP BY reason " +
            "HAVING count(reason) = (SELECT count(reason) FROM roadaccidents " +
            "   GROUP BY reason " +
            "   ORDER BY count(reason) DESC " +
            "   LIMIT 1)", nativeQuery = true)
    List<String> findMostPopularReason();

    @Query(value = "WITH reasons AS (SELECT count(reason) as all_reasons FROM roadaccidents) " +
            "SELECT count(reason) as drunk_reason, count(reason) * 1.0 / all_reasons AS percents " +
            "FROM RoadAccidents CROSS JOIN reasons " +
            "GROUP BY reason, all_reasons " +
            "HAVING reason = 'Пьяный водитель'", nativeQuery = true)
    List<Object[]> getDrunkDrivingAccidentsAndPercentage();

    @Query(value = "SELECT number FROM RoadAccidents " +
            "JOIN TransportsDamagedInAccident ON RoadAccidents.id = TransportsDamagedInAccident.accident_id " +
            "JOIN TransportNumberDirectory ON TransportsDamagedInAccident.transport_id = TransportNumberDirectory.id " +
            "WHERE did_run_away " +
            "UNION " +
            "SELECT number FROM Hijackings " +
            "JOIN HijackingsResult ON Hijackings.result_id = HijackingsResult.id " +
            "JOIN TransportNumberDirectory ON Hijackings.transport_id = TransportNumberDirectory.id " +
            "WHERE result_name = 'Подозреваемый на свободе'", nativeQuery = true)
    List<String> getWantedVehicles();
}
