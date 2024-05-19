package com.trafficpolice.dbback.mapper;

import com.trafficpolice.dbback.dto.AccidentTypesDTO;
import com.trafficpolice.dbback.entity.AccidentTypes;
import org.springframework.stereotype.Component;

@Component
public class AccidentTypesMapper {

    public AccidentTypesDTO toDTO(AccidentTypes accidentTypes) {
        AccidentTypesDTO dto = new AccidentTypesDTO();
        dto.setId(accidentTypes.getId());
        dto.setName(accidentTypes.getName());
        return dto;
    }

    public AccidentTypes toEntity(AccidentTypesDTO dto) {
        AccidentTypes accidentTypes = new AccidentTypes();
        accidentTypes.setId(dto.getId());
        accidentTypes.setName(dto.getName());
        return accidentTypes;
    }
}
