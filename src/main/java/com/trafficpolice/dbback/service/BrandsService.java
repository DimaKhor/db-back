package com.trafficpolice.dbback.service;

import com.trafficpolice.dbback.dto.BrandsDTO;
import com.trafficpolice.dbback.entity.Brands;
import com.trafficpolice.dbback.mapper.BrandsMapper;
import com.trafficpolice.dbback.repository.BrandsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BrandsService {

    private final BrandsRepository brandsRepository;
    private final BrandsMapper brandsMapper;

    public BrandsDTO findById(int id) {
        Optional<Brands> brand = brandsRepository.findById(id);
        return brand.map(brandsMapper::toDTO).orElse(null);
    }

    public List<BrandsDTO> findAll() {
        List<Brands> brandsList = brandsRepository.findAll();
        return brandsList.stream()
                .map(brandsMapper::toDTO)
                .collect(Collectors.toList());
    }
}
