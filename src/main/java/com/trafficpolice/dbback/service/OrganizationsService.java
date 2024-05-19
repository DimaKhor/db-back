package com.trafficpolice.dbback.service;

import com.trafficpolice.dbback.dto.OrganizationsDTO;
import com.trafficpolice.dbback.entity.Organizations;
import com.trafficpolice.dbback.mapper.OrganizationsMapper;
import com.trafficpolice.dbback.repository.OrganizationsRepository;
import jakarta.persistence.EntityNotFoundException;
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
        Organizations organization = organizationsMapper.toEntity(organizationDTO);
        Organizations savedOrganization = organizationsRepository.save(organization);
        return organizationsMapper.toDTO(savedOrganization);
    }

    public void deleteOrganization(int id) {
        organizationsRepository.deleteById(id);
    }

    public OrganizationsDTO updateOrganization(int id, OrganizationsDTO organizationDTO) {
        Organizations existingOrganization = organizationsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Organization not found with id: " + id));

        // Обновление существующей сущности с помощью данных из DTO
        existingOrganization.setName(organizationDTO.getName());
        existingOrganization.setArea(organizationDTO.getArea());
        existingOrganization.setStreet(organizationDTO.getStreet());
        existingOrganization.setBuilding(organizationDTO.getBuilding());
        existingOrganization.setHeadLastName(organizationDTO.getHeadLastName());
        existingOrganization.setHeadName(organizationDTO.getHeadName());
        existingOrganization.setHeadFatherName(organizationDTO.getHeadFatherName());

        // Сохранение обновленной сущности в базе данных
        Organizations updatedOrganization = organizationsRepository.save(existingOrganization);

        return organizationsMapper.toDTO(updatedOrganization);
    }



}
