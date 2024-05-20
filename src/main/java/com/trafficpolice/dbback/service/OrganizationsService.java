package com.trafficpolice.dbback.service;

import com.trafficpolice.dbback.dto.OrganizationsDTO;
import com.trafficpolice.dbback.entity.Organizations;
import com.trafficpolice.dbback.mapper.OrganizationsMapper;
import com.trafficpolice.dbback.repository.OrganizationsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public List<String> findOrganizationNamesBySeriesOrPeriod(String series, LocalDate startDate, LocalDate endDate) {
        return organizationsRepository.findOrganizationNamesBySeriesOrPeriod(series, startDate, endDate);
    }

    public int countOrganizationNamesBySeriesOrPeriod(String series, LocalDate startDate, LocalDate endDate) {
        return organizationsRepository.countOrganizationNamesBySeriesOrPeriod(series, startDate, endDate);
    }

    public OrganizationsDTO addOrganization(OrganizationsDTO organizationDTO) {
        try {
            Organizations organization = organizationsMapper.toEntity(organizationDTO);
            Organizations savedOrganization = organizationsRepository.save(organization);
            return organizationsMapper.toDTO(savedOrganization);
        } catch (Exception e) {
            throw new RuntimeException("Failed to add organization: " + e.getMessage(), e);
        }
    }

    public void deleteOrganization(int id) {
        try {
            if (organizationsRepository.existsById(id)) {
                organizationsRepository.deleteById(id);
            } else {
                throw new RuntimeException("Organization not found with id: " + id);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete organization with id " + id + ": " + e.getMessage(), e);
        }
    }

    public OrganizationsDTO updateOrganization(int id, OrganizationsDTO organizationDTO) {
        try {
            Organizations existingOrganization = organizationsRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Organization not found with id: " + id));

            existingOrganization.setName(organizationDTO.getName());
            existingOrganization.setArea(organizationDTO.getArea());
            existingOrganization.setStreet(organizationDTO.getStreet());
            existingOrganization.setBuilding(organizationDTO.getBuilding());
            existingOrganization.setHeadLastName(organizationDTO.getHeadLastName());
            existingOrganization.setHeadName(organizationDTO.getHeadName());
            existingOrganization.setHeadFatherName(organizationDTO.getHeadFatherName());

            Organizations updatedOrganization = organizationsRepository.save(existingOrganization);

            return organizationsMapper.toDTO(updatedOrganization);
        } catch (Exception e) {
            throw new RuntimeException("Failed to update organization with id " + id + ": " + e.getMessage(), e);
        }
    }
}
