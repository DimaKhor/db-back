package com.trafficpolice.dbback.mapper;

import com.trafficpolice.dbback.dto.RoadAccidentsDTO;
import com.trafficpolice.dbback.entity.RoadAccidents;
import org.springframework.stereotype.Component;

@Component
public class RoadAccidentsMapper {

    public RoadAccidentsDTO toDTO(RoadAccidents accidents) {
        RoadAccidentsDTO dto = new RoadAccidentsDTO();
        dto.setId(accidents.getId());
        dto.setDate(accidents.getDate().toString());
        dto.setPlace(accidents.getPlace());
        dto.setBrief(accidents.getBrief());
        dto.setVictimsNumber(accidents.getVictimsNumber());
        dto.setDamagePrice(accidents.getDamagePrice());
        dto.setReason(accidents.getReason());
        dto.setRoadConditions(accidents.getRoadConditions());
        dto.setTypeId(accidents.getAccidentType().getId());
        return dto;
    }
}
