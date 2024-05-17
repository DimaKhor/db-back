package com.trafficpolice.dbback.repository;

import com.trafficpolice.dbback.entity.Organizations;
import com.trafficpolice.dbback.repository.OrganizationsRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationsRepository extends JpaRepository<Organizations, Integer>, OrganizationsRepositoryCustom {

}
