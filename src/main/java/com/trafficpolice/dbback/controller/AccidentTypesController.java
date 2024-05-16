package com.trafficpolice.dbback.controller;

import com.trafficpolice.dbback.dto.AccidentTypesDTO;
import com.trafficpolice.dbback.service.AccidentTypesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AccidentTypesController {

    private final AccidentTypesService service;

    @GetMapping("/accidenttypes/{id}")
    public ResponseEntity<AccidentTypesDTO> getAccidentTypeById(@PathVariable int id) {
        try {
            AccidentTypesDTO dto = service.findById(id);
            return ResponseEntity.ok(dto);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/accidenttypes")
    public ResponseEntity<List<AccidentTypesDTO>> getAllAccidentTypes() {
        try {
            List<AccidentTypesDTO> dtos = service.findAll();
            return ResponseEntity.ok(dtos);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }
}
