// OrganizationsController.java

package com.trafficpolice.dbback.controller;

import com.trafficpolice.dbback.dto.OrganizationsDTO;
import com.trafficpolice.dbback.service.OrganizationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
}
