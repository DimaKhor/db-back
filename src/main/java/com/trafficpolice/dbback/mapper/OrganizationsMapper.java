package com.trafficpolice.dbback.mapper;

import com.trafficpolice.dbback.dto.OrganizationsDTO;
import com.trafficpolice.dbback.entity.Organizations;
import org.springframework.stereotype.Component;

@Component
public class OrganizationsMapper {

    public OrganizationsDTO toDTO(Organizations organization) {
        OrganizationsDTO organizationsDTO = new OrganizationsDTO();
        organizationsDTO.setId(organization.getId());
        organizationsDTO.setName(organization.getName());
        organizationsDTO.setArea(organization.getArea());
        organizationsDTO.setStreet(organization.getStreet());
        organizationsDTO.setBuilding(organization.getBuilding());
        organizationsDTO.setHeadLastName(organization.getHeadLastName());
        organizationsDTO.setHeadName(organization.getHeadName());
        organizationsDTO.setHeadFatherName(organization.getHeadFatherName());
        return organizationsDTO;
    }

    public Organizations toEntity(OrganizationsDTO organizationDTO) {
        Organizations organization = new Organizations();
        organization.setId(organizationDTO.getId());
        organization.setName(organizationDTO.getName());
        organization.setArea(organizationDTO.getArea());
        organization.setStreet(organizationDTO.getStreet());
        organization.setBuilding(organizationDTO.getBuilding());
        organization.setHeadLastName(organizationDTO.getHeadLastName());
        organization.setHeadName(organizationDTO.getHeadName());
        organization.setHeadFatherName(organizationDTO.getHeadFatherName());
        return organization;
    }
}
