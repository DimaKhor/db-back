package com.trafficpolice.dbback.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BrandsDTO {
    private int id;
    private String name;

    public BrandsDTO() {
    }

    public BrandsDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
