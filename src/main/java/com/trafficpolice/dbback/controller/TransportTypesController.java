package com.trafficpolice.dbback.controller;

import com.trafficpolice.dbback.dto.TransportTypesDTO;
import com.trafficpolice.dbback.service.TransportTypesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TransportTypesController {

    private final TransportTypesService transportTypesService;

    @GetMapping("/transporttypes/{id}")
    public ResponseEntity<TransportTypesDTO> getTransportTypeById(@PathVariable int id) {
        TransportTypesDTO transportTypesDTO = transportTypesService.findById(id);
        return ResponseEntity.ok(transportTypesDTO);
    }

    @GetMapping("/transporttypes")
    public ResponseEntity<List<TransportTypesDTO>> getAllTransportTypes() {
        List<TransportTypesDTO> dtos = transportTypesService.findAll();
        return ResponseEntity.ok(dtos);
    }

    @PostMapping("/transporttypes")
    public ResponseEntity<?> createTransportType(@RequestBody TransportTypesDTO transportTypesDTO) {
        try {
            TransportTypesDTO createdTransportTypesDTO = transportTypesService.save(transportTypesDTO);
            return ResponseEntity.ok(createdTransportTypesDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/transporttypes/{id}")
    public ResponseEntity<?> updateTransportType(@PathVariable int id, @RequestBody TransportTypesDTO transportTypesDTO) {
        try {
            TransportTypesDTO updatedTransportTypesDTO = transportTypesService.update(id, transportTypesDTO);
            return ResponseEntity.ok(updatedTransportTypesDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/transporttypes/{id}")
    public ResponseEntity<?> deleteTransportType(@PathVariable int id) {
        try {
            transportTypesService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
