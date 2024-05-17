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
}
