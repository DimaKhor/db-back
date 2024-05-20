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

    public BrandsDTO save(BrandsDTO brandDTO) {
        try {
            Brands brand = brandsMapper.toEntity(brandDTO);
            Brands savedBrand = brandsRepository.save(brand);
            return brandsMapper.toDTO(savedBrand);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save brand: " + e.getMessage(), e);
        }
    }

    public BrandsDTO update(int id, BrandsDTO brandDTO) {
        try {
            Brands existingBrand = brandsRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Brand not found with id: " + id));

            existingBrand.setName(brandDTO.getName());
            Brands updatedBrand = brandsRepository.save(existingBrand);
            return brandsMapper.toDTO(updatedBrand);
        } catch (Exception e) {
            throw new RuntimeException("Failed to update brand with id " + id + ": " + e.getMessage(), e);
        }
    }

    public void deleteById(int id) {
        try {
            brandsRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete brand with id " + id + ": " + e.getMessage(), e);
        }
    }
}
