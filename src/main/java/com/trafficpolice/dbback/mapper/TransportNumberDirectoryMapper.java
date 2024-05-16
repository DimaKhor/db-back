package com.trafficpolice.dbback.mapper;

import com.trafficpolice.dbback.dto.TransportNumberDirectoryDTO;
import com.trafficpolice.dbback.entity.TransportNumberDirectory;
import org.springframework.stereotype.Component;

@Component
public class TransportNumberDirectoryMapper {

    public TransportNumberDirectoryDTO toDTO(TransportNumberDirectory directory) {
        TransportNumberDirectoryDTO dto = new TransportNumberDirectoryDTO();
        dto.setId(directory.getId());
        dto.setTransportTypeId(directory.getTransportType().getId());
        dto.setPersonId(directory.getPerson().getId());
        dto.setOrganizationId(directory.getOrganization() != null ?
                directory.getOrganization().getId() : null);
        dto.setBrandId(directory.getBrand().getId());
        dto.setColorId(directory.getColor().getId());
        dto.setIssueDate(directory.getIssueDate().toString());
        dto.setEngineCapacity(directory.getEngineCapacity());
        dto.setEngineId(directory.getEngineId());
        dto.setChassisId(directory.getChassisId());
        dto.setCoachbuilderId(directory.getCoachbuilderId());
        dto.setNumber(directory.getNumber());
        return dto;
    }
}
