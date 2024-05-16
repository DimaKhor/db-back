package com.trafficpolice.dbback.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransportTypesDTO {
    private int id;
    private String name;

    public TransportTypesDTO() {
    }

    public TransportTypesDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
