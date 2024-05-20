package com.trafficpolice.dbback.repository;

import com.trafficpolice.dbback.entity.Persons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PersonsRepository extends JpaRepository<Persons, Integer> {

    @Query("SELECT p FROM Persons p JOIN p.transportNumbers tnd WHERE tnd.number = :number")
    Persons findByTransportNumber(@Param("number") String number);

}
