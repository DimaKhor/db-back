package com.trafficpolice.dbback.mapper;

import com.trafficpolice.dbback.dto.BrandsDTO;
import com.trafficpolice.dbback.entity.Brands;
import org.springframework.stereotype.Component;

@Component
public class BrandsMapper {

    public BrandsDTO toDTO(Brands brand) {
        BrandsDTO brandsDTO = new BrandsDTO();
        brandsDTO.setId(brand.getId());
        brandsDTO.setName(brand.getName());
        return brandsDTO;
    }

    public Brands toEntity(BrandsDTO brandDTO) {
        Brands brand = new Brands();
        brand.setId(brandDTO.getId());
        brand.setName(brandDTO.getName());
        return brand;
    }
}
