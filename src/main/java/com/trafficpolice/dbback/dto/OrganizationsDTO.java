package com.trafficpolice.dbback.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrganizationsDTO {
    private int id;
    private String name;
    private String area;
    private String street;
    private String building;
    private String headLastName;
    private String headName;
    private String headFatherName;
}
