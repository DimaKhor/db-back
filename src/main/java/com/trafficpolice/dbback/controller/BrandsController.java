// BrandsController.java

package com.trafficpolice.dbback.controller;

import com.trafficpolice.dbback.dto.BrandsDTO;
import com.trafficpolice.dbback.service.BrandsService;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<BrandsDTO> createBrand(@RequestBody BrandsDTO brandDTO) {
        BrandsDTO createdBrandDTO = brandsService.save(brandDTO);
        return ResponseEntity.ok(createdBrandDTO);
    }

    @PutMapping("/brands/{id}")
    public ResponseEntity<BrandsDTO> updateBrand(@PathVariable int id, @RequestBody BrandsDTO brandDTO) {
        BrandsDTO updatedBrandDTO = brandsService.update(id, brandDTO);
        return ResponseEntity.ok(updatedBrandDTO);
    }

    @DeleteMapping("/brands/{id}")
    public ResponseEntity<Void> deleteBrand(@PathVariable int id) {
        brandsService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
