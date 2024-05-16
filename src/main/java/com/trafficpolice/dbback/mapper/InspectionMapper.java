package com.trafficpolice.dbback.mapper;

import com.trafficpolice.dbback.dto.InspectionDTO;
import com.trafficpolice.dbback.entity.Inspection;
import org.springframework.stereotype.Component;

@Component
public class InspectionMapper {

    public InspectionDTO toDTO(Inspection inspection) {
        InspectionDTO dto = new InspectionDTO();
        dto.setId(inspection.getId());
        dto.setTransportId(inspection.getTransport().getId());
        dto.setPaymentForLiter(inspection.getPaymentForLiter());
        dto.setPeriodicityInMonths(inspection.getPeriodicityInMonths());
        dto.setLastTime(inspection.getLastTime());
        return dto;
    }
}
