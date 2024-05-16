// ColorsController.java

package com.trafficpolice.dbback.controller;

import com.trafficpolice.dbback.dto.ColorsDTO;
import com.trafficpolice.dbback.service.ColorsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
}
