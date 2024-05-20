package com.trafficpolice.dbback.controller;

import com.trafficpolice.dbback.dto.ColorsDTO;
import com.trafficpolice.dbback.entity.Colors;
import com.trafficpolice.dbback.service.ColorsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ColorsController {

    private final ColorsService colorsService;

    @GetMapping("/colors/{id}")
    public ResponseEntity<ColorsDTO> getColorById(@PathVariable int id) {
        ColorsDTO colorsDTO = colorsService.findById(id);
        return ResponseEntity.ok(colorsDTO);
    }

    @GetMapping("/colors")
    public ResponseEntity<List<ColorsDTO>> getAllColors() {
        List<ColorsDTO> colorsDTOList = colorsService.findAll();
        return ResponseEntity.ok(colorsDTOList);
    }

    @PostMapping("/colors")
    public ResponseEntity<?> addColor(@RequestBody ColorsDTO colorsDTO) {
        try {
            ColorsDTO addedColor = colorsService.addColor(colorsDTO);
            return ResponseEntity.ok(addedColor);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/colors/{id}")
    public ResponseEntity<?> deleteColor(@PathVariable int id) {
        try {
            colorsService.deleteColorById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/colors/{id}")
    public ResponseEntity<?> updateColor(@PathVariable int id, @RequestBody Colors color) {
        try {
            Colors updatedColor = colorsService.updateColor(id, color.getName());
            return ResponseEntity.ok(updatedColor);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
