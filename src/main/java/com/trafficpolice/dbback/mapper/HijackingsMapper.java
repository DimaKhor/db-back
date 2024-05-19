package com.trafficpolice.dbback.mapper;

import com.trafficpolice.dbback.dto.HijackingsDTO;
import com.trafficpolice.dbback.entity.Hijackings;
import com.trafficpolice.dbback.entity.HijackingsResult;
import com.trafficpolice.dbback.entity.TransportNumberDirectory;
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

    public Hijackings toEntity(HijackingsDTO dto) {
        Hijackings hijackings = new Hijackings();
        hijackings.setId(dto.getId());
        TransportNumberDirectory transport = new TransportNumberDirectory();
        transport.setId(dto.getTransportId());
        hijackings.setTransport(transport);
        HijackingsResult result = new HijackingsResult();
        result.setId(dto.getResultId());
        hijackings.setResult(result);
        hijackings.setSignaling(dto.getSignaling());
        hijackings.setDate(dto.getDate());
        return hijackings;
    }
}
