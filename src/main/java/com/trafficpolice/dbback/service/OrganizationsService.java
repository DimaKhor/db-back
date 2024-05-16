package com.trafficpolice.dbback.service;

import com.trafficpolice.dbback.dto.OrganizationsDTO;
import com.trafficpolice.dbback.entity.Organizations;
import com.trafficpolice.dbback.mapper.OrganizationsMapper;
import com.trafficpolice.dbback.repository.OrganizationsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrganizationsService {

    private final OrganizationsRepository organizationsRepository;
    private final OrganizationsMapper organizationsMapper;

    public OrganizationsDTO findById(int id) {
        Optional<Organizations> organization = organizationsRepository.findById(id);
        return organization.map(organizationsMapper::toDTO).orElse(null);
    }

    public List<OrganizationsDTO> findAll() {
        List<Organizations> organizationsList = organizationsRepository.findAll();
        return organizationsList.stream()
                .map(organizationsMapper::toDTO)
                .collect(Collectors.toList());
    }
}
