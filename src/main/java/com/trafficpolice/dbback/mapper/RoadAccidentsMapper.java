package com.trafficpolice.dbback.mapper;

import com.trafficpolice.dbback.dto.RoadAccidentsDTO;
import com.trafficpolice.dbback.entity.AccidentTypes;
import com.trafficpolice.dbback.entity.RoadAccidents;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    public RoadAccidents toEntity(RoadAccidentsDTO dto) {
        RoadAccidents accidents = new RoadAccidents();
        accidents.setId(dto.getId());
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date issueDate = dateFormat.parse(dto.getDate());
            accidents.setDate(issueDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        accidents.setPlace(dto.getPlace());
        accidents.setBrief(dto.getBrief());
        accidents.setVictimsNumber(dto.getVictimsNumber());
        accidents.setDamagePrice(dto.getDamagePrice());
        accidents.setReason(dto.getReason());
        accidents.setRoadConditions(dto.getRoadConditions());
        AccidentTypes accidentType = new AccidentTypes();
        accidentType.setId(dto.getTypeId());
        accidents.setAccidentType(accidentType);
        return accidents;
    }
}
