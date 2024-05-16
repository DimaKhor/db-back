// BrandsController.java

package com.trafficpolice.dbback.controller;

import com.trafficpolice.dbback.dto.BrandsDTO;
import com.trafficpolice.dbback.service.BrandsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
}
