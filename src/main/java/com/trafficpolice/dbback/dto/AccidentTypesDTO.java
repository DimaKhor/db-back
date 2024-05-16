package com.trafficpolice.dbback.dto;

import lombok.*;

@Getter
@Setter
public class AccidentTypesDTO {
    private int id;
    private String name;

    public AccidentTypesDTO() {
    }

    public AccidentTypesDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
