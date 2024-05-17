package com.trafficpolice.dbback.repository;

import java.time.LocalDate;
import java.util.List;

public interface OrganizationsRepositoryCustom {
    List<String> findOrganizationNamesBySeriesOrPeriod(String series, LocalDate startDate, LocalDate endDate);
    int countOrganizationNamesBySeriesOrPeriod(String series, LocalDate startDate, LocalDate endDate);
}
