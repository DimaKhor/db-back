package com.trafficpolice.dbback.mapper;

import com.trafficpolice.dbback.dto.ColorsDTO;
import com.trafficpolice.dbback.entity.Colors;
import org.springframework.stereotype.Component;

@Component
public class ColorsMapper {

    public ColorsDTO toDTO(Colors colors) {
        ColorsDTO colorsDTO = new ColorsDTO();
        colorsDTO.setId(colors.getId());
        colorsDTO.setName(colors.getName());
        return colorsDTO;
    }

    public Colors toEntity(ColorsDTO colorsDTO) {
        Colors colors = new Colors();
        colors.setId(colorsDTO.getId());
        colors.setName(colorsDTO.getName());
        return colors;
    }
}