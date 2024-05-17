package com.trafficpolice.dbback.repository;

import com.trafficpolice.dbback.entity.Brands;
import com.trafficpolice.dbback.entity.Hijackings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface HijackingsRepository extends JpaRepository<Hijackings, Integer> {
    @Query(value = """
            WITH Successful AS (
                SELECT sum(count) AS successful 
                FROM (
                    SELECT count(Hijackings.id) 
                    FROM Hijackings
                    JOIN HijackingsResult ON Hijackings.result_id = HijackingsResult.id
                    WHERE result_name = 'Арестован'
                    UNION 
                    SELECT count(*) 
                    FROM transportsdamagedinaccident
                    WHERE did_run_away AND found
                )
            ), 
            Total AS (
                SELECT sum(count) AS total 
                FROM (
                    SELECT count(*) 
                    FROM transportsdamagedinaccident
                    WHERE did_run_away
                    UNION
                    SELECT count(*) 
                    FROM Hijackings
                )
            )
            SELECT ROUND((successful * 1.0)/total, 2) AS efficiency 
            FROM Successful
            CROSS JOIN Total
            """, nativeQuery = true)
    Double getSearchEfficiency();

    @Query(value = """
        SELECT number AS car_number 
        FROM Hijackings
        JOIN TransportNumberDirectory ON Hijackings.transport_id = TransportNumberDirectory.id
        WHERE date BETWEEN CAST(:startDate AS DATE) AND CAST(:endDate AS DATE)
        """, nativeQuery = true)
    List<String> findHijackingsByPeriod(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query(value = "SELECT signaling AS most_popular_signaling FROM Hijackings " +
            "GROUP BY signaling " +
            "HAVING count(signaling) = (SELECT count(signaling) FROM Hijackings " +
            "GROUP BY signaling " +
            "ORDER BY count(signaling) DESC " +
            "LIMIT 1)", nativeQuery = true)
    List<String> findMostPopularSignaling();

    @Query(value = "SELECT Brands.name AS most_stolen_brand " +
            "FROM Hijackings " +
            "JOIN TransportNumberDirectory ON Hijackings.transport_id = TransportNumberDirectory.id " +
            "JOIN Brands ON TransportNumberDirectory.brand_id = Brands.id " +
            "GROUP BY Brands.name " +
            "HAVING count(Brands.name) = (SELECT count(Brands.name) AS count1  FROM Hijackings " +
            "JOIN TransportNumberDirectory ON Hijackings.transport_id = TransportNumberDirectory.id " +
            "JOIN Brands ON TransportNumberDirectory.brand_id = Brands.id " +
            "GROUP BY Brands.name " +
            "ORDER BY count(Brands.name) DESC " +
            "LIMIT 1)",
            nativeQuery = true)
    List<String> findMostStolenBrandNames();
}

