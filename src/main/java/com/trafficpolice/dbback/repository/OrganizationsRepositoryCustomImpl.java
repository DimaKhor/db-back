package com.trafficpolice.dbback.repository;

import com.trafficpolice.dbback.repository.OrganizationsRepositoryCustom;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;
public class OrganizationsRepositoryCustomImpl implements OrganizationsRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<String> findOrganizationNamesBySeriesOrPeriod(String series, LocalDate startDate, LocalDate endDate) {
        Query query = entityManager.createQuery("SELECT o.name FROM Organizations o WHERE o.id IN (SELECT DISTINCT td.organization.id FROM TransportNumberDirectory td WHERE td.organization IS NOT NULL AND (td.issueDate BETWEEN :startDate AND :endDate) OR td.number LIKE CONCAT('%', :series, '%'))");
        query.setParameter("series", series);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        return query.getResultList();
    }

    @Override
    public int countOrganizationNamesBySeriesOrPeriod(String series, LocalDate startDate, LocalDate endDate) {
        List<String> organizationNames = findOrganizationNamesBySeriesOrPeriod(series, startDate, endDate);
        return organizationNames.size();
    }
}

