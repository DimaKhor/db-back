package com.trafficpolice.dbback.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ColorsDTO {
    private int id;
    private String name;

    public ColorsDTO() {
    }

    public ColorsDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
