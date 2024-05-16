package com.trafficpolice.dbback.mapper;

import com.trafficpolice.dbback.dto.HijackingsDTO;
import com.trafficpolice.dbback.entity.Hijackings;
import org.springframework.stereotype.Component;

@Component
public class HijackingsMapper {

    public HijackingsDTO toDTO(Hijackings hijackings) {
        HijackingsDTO dto = new HijackingsDTO();
        dto.setId(hijackings.getId());
        dto.setTransportId(hijackings.getTransport().getId());
        dto.setResultId(hijackings.getResult().getId());
        dto.setSignaling(hijackings.getSignaling());
        dto.setDate(hijackings.getDate());
        return dto;
    }
}
