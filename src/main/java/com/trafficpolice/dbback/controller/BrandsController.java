package com.trafficpolice.dbback.controller;

import com.trafficpolice.dbback.dto.BrandsDTO;
import com.trafficpolice.dbback.service.BrandsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BrandsController {

    private final BrandsService brandsService;

    @GetMapping("/brands/{id}")
    public ResponseEntity<BrandsDTO> getBrandById(@PathVariable int id) {
        BrandsDTO brandsDTO = brandsService.findById(id);
        return ResponseEntity.ok(brandsDTO);
    }

    @GetMapping("/brands")
    public ResponseEntity<List<BrandsDTO>> getAllBrands() {
        List<BrandsDTO> brandsDTOList = brandsService.findAll();
        return ResponseEntity.ok(brandsDTOList);
    }

    @PostMapping("/brands")
    public ResponseEntity<?> createBrand(@RequestBody BrandsDTO brandDTO) {
        try {
            BrandsDTO createdBrandDTO = brandsService.save(brandDTO);
            return ResponseEntity.ok(createdBrandDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/brands/{id}")
    public ResponseEntity<?> updateBrand(@PathVariable int id, @RequestBody BrandsDTO brandDTO) {
        try {
            BrandsDTO updatedBrandDTO = brandsService.update(id, brandDTO);
            return ResponseEntity.ok(updatedBrandDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/brands/{id}")
    public ResponseEntity<?> deleteBrand(@PathVariable int id) {
        try {
            brandsService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
