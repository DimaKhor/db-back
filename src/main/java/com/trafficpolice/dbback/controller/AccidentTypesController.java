package com.trafficpolice.dbback.controller;

import com.trafficpolice.dbback.dto.AccidentTypesDTO;
import com.trafficpolice.dbback.service.AccidentTypesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/accidenttypes")
    public ResponseEntity<?> createAccidentType(@RequestBody AccidentTypesDTO dto) {
        try {
            AccidentTypesDTO createdDto = service.save(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdDto);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/accidenttypes/{id}")
    public ResponseEntity<?> updateAccidentType(@PathVariable int id, @RequestBody AccidentTypesDTO dto) {
        try {
            AccidentTypesDTO updatedDto = service.update(id, dto);
            return ResponseEntity.ok(updatedDto);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/accidenttypes/{id}")
    public ResponseEntity<?> deleteAccidentType(@PathVariable int id) {
        try {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
