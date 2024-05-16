package com.trafficpolice.dbback.mapper;

import com.trafficpolice.dbback.dto.HijackingsResultDTO;
import com.trafficpolice.dbback.entity.HijackingsResult;
import org.springframework.stereotype.Component;

@Component
public class HijackingsResultMapper {

    public HijackingsResultDTO toDTO(HijackingsResult hijackingsResult) {
        HijackingsResultDTO dto = new HijackingsResultDTO();
        dto.setId(hijackingsResult.getId());
        dto.setResultName(hijackingsResult.getResultName());
        return dto;
    }
}
