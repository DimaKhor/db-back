package com.trafficpolice.dbback.repository;

import com.trafficpolice.dbback.entity.Brands;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandsRepository extends JpaRepository<Brands, Integer> {
}
