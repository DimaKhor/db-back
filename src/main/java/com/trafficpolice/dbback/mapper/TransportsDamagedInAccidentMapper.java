package com.trafficpolice.dbback.mapper;

import com.trafficpolice.dbback.dto.TransportsDamagedInAccidentDTO;
import com.trafficpolice.dbback.entity.TransportsDamagedInAccident;
import org.springframework.stereotype.Component;

@Component
public class TransportsDamagedInAccidentMapper {

    public TransportsDamagedInAccidentDTO toDTO(TransportsDamagedInAccident transportsDamagedInAccident) {
        TransportsDamagedInAccidentDTO dto = new TransportsDamagedInAccidentDTO();
        dto.setAccidentId(transportsDamagedInAccident.getRoadAccident().getId());
        dto.setTransportId(transportsDamagedInAccident.getTransportNumberDirectory().getId());
        dto.setDidRunAway(transportsDamagedInAccident.isDidRunAway());
        dto.setFound(transportsDamagedInAccident.getFound());
        return dto;
    }
}
