package com.trafficpolice.dbback.mapper;

import com.trafficpolice.dbback.dto.TransportsDamagedInAccidentDTO;
import com.trafficpolice.dbback.entity.RoadAccidents;
import com.trafficpolice.dbback.entity.TransportNumberDirectory;
import com.trafficpolice.dbback.entity.TransportsDamagedInAccident;
import com.trafficpolice.dbback.entity.TransportsDamagedInAccidentId;
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

    public TransportsDamagedInAccident toEntity(TransportsDamagedInAccidentDTO dto, RoadAccidents roadAccident, TransportNumberDirectory transportNumberDirectory) {
        TransportsDamagedInAccident transportsDamagedInAccident = new TransportsDamagedInAccident();
        transportsDamagedInAccident.setId(new TransportsDamagedInAccidentId(dto.getAccidentId(), dto.getTransportId()));
        transportsDamagedInAccident.setRoadAccident(roadAccident);
        transportsDamagedInAccident.setTransportNumberDirectory(transportNumberDirectory);
        transportsDamagedInAccident.setDidRunAway(dto.isDidRunAway());
        transportsDamagedInAccident.setFound(dto.getFound());
        return transportsDamagedInAccident;
    }
}
