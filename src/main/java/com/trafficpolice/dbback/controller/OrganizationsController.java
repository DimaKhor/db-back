package com.trafficpolice.dbback.controller;

import com.trafficpolice.dbback.dto.OrganizationsDTO;
import com.trafficpolice.dbback.service.OrganizationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrganizationsController {

    private final OrganizationsService organizationsService;

    @GetMapping("/organizations/{id}")
    public ResponseEntity<OrganizationsDTO> getOrganizationById(@PathVariable int id) {
        OrganizationsDTO organizationsDTO = organizationsService.findById(id);
        return ResponseEntity.ok(organizationsDTO);
    }

    @GetMapping("/organizations")
    public ResponseEntity<List<OrganizationsDTO>> getAllOrganizations() {
        List<OrganizationsDTO> organizationsDTOList = organizationsService.findAll();
        return ResponseEntity.ok(organizationsDTOList);
    }

    //запрос 1

    // http://localhost:8080/organizations/by-series-or-period?series=ABC
    // http://localhost:8080/organizations/by-series-or-period?startDate=2022-01-01&endDate=2022-01-10
    // http://localhost:8080/organizations/by-series-or-period?series=ABC&startDate=2022-01-01&endDate=2022-01-10
    @GetMapping("/organizations/by-series-or-period")
    public ResponseEntity<List<String>> getOrganizationNamesBySeriesOrPeriod(
            @RequestParam(required = false) String series,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate
    ) {
        List<String> organizationNames = organizationsService.findOrganizationNamesBySeriesOrPeriod(series, startDate, endDate);
        return ResponseEntity.ok(organizationNames);
    }

    @GetMapping("/organizations/count-by-series-or-period")
    public ResponseEntity<Integer> countOrganizationNamesBySeriesOrPeriod(
            @RequestParam(required = false) String series,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate
    ) {
        int count = organizationsService.countOrganizationNamesBySeriesOrPeriod(series, startDate, endDate);
        return ResponseEntity.ok(count);
    }

    @PostMapping("/organizations")
    public ResponseEntity<OrganizationsDTO> addOrganization(@RequestBody OrganizationsDTO organizationDTO) {
        OrganizationsDTO createdOrganization = organizationsService.addOrganization(organizationDTO);
        return ResponseEntity.ok(createdOrganization);
    }

    @DeleteMapping("/organizations/{id}")
    public ResponseEntity<Void> deleteOrganization(@PathVariable int id) {
        organizationsService.deleteOrganization(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/organizations/{id}")
    public ResponseEntity<OrganizationsDTO> updateOrganization(@PathVariable int id, @RequestBody OrganizationsDTO organizationDTO) {
        OrganizationsDTO updatedOrganization = organizationsService.updateOrganization(id, organizationDTO);
        return ResponseEntity.ok(updatedOrganization);
    }
}