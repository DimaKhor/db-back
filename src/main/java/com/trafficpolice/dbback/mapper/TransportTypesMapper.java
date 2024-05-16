package com.trafficpolice.dbback.mapper;

import com.trafficpolice.dbback.dto.TransportTypesDTO;
import com.trafficpolice.dbback.entity.TransportTypes;
import org.springframework.stereotype.Component;

@Component
public class TransportTypesMapper {

    public TransportTypesDTO toDTO(TransportTypes transportTypes) {
        TransportTypesDTO transportTypesDTO = new TransportTypesDTO();
        transportTypesDTO.setId(transportTypes.getId());
        transportTypesDTO.setName(transportTypes.getName());
        return transportTypesDTO;
    }

    public TransportTypes toEntity(TransportTypesDTO transportTypesDTO) {
        TransportTypes transportTypes = new TransportTypes();
        transportTypes.setId(transportTypesDTO.getId());
        transportTypes.setName(transportTypesDTO.getName());
        return transportTypes;
    }
}
