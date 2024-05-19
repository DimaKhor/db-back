// ColorsController.java

package com.trafficpolice.dbback.controller;

import com.trafficpolice.dbback.dto.ColorsDTO;
import com.trafficpolice.dbback.entity.Colors;
import com.trafficpolice.dbback.service.ColorsService;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<ColorsDTO> addColor(@RequestBody ColorsDTO colorsDTO) {
        ColorsDTO addedColor = colorsService.addColor(colorsDTO);
        return ResponseEntity.ok(addedColor);
    }

    @DeleteMapping("/colors/{id}")
    public ResponseEntity<Void> deleteColor(@PathVariable int id) {
        colorsService.deleteColorById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/colors/{id}")
    public Colors updateColor(@PathVariable int id, @RequestBody Colors color) {
        return colorsService.updateColor(id, color.getName());
    }
}
