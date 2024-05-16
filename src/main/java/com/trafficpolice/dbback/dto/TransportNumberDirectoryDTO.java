package com.trafficpolice.dbback.dto;

import lombok.*;

@Getter
@Setter
public class TransportNumberDirectoryDTO {
    private int id;
    private int transportTypeId;
    private int personId;
    private Integer organizationId;
    private int brandId;
    private int colorId;
    private String issueDate;
    private int engineCapacity;
    private String engineId;
    private String chassisId;
    private String coachbuilderId;
    private String number;
}
