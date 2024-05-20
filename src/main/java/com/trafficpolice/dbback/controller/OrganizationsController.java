package com.trafficpolice.dbback.controller;

import com.trafficpolice.dbback.dto.OrganizationsDTO;
import com.trafficpolice.dbback.service.OrganizationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> addOrganization(@RequestBody OrganizationsDTO organizationDTO) {
        try {
            OrganizationsDTO createdOrganization = organizationsService.addOrganization(organizationDTO);
            return ResponseEntity.ok(createdOrganization);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add organization: " + e.getMessage());
        }
    }

    @DeleteMapping("/organizations/{id}")
    public ResponseEntity<?> deleteOrganization(@PathVariable int id) {
        try {
            organizationsService.deleteOrganization(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete organization with id " + id + ": " + e.getMessage());
        }
    }

    @PutMapping("/organizations/{id}")
    public ResponseEntity<?> updateOrganization(@PathVariable int id, @RequestBody OrganizationsDTO organizationDTO) {
        try {
            OrganizationsDTO updatedOrganization = organizationsService.updateOrganization(id, organizationDTO);
            return ResponseEntity.ok(updatedOrganization);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update organization with id " + id + ": " + e.getMessage());
        }
    }

}