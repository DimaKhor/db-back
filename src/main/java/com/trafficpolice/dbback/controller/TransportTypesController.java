package com.trafficpolice.dbback.controller;

import com.trafficpolice.dbback.dto.TransportTypesDTO;
import com.trafficpolice.dbback.service.TransportTypesService;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<TransportTypesDTO> createTransportType(@RequestBody TransportTypesDTO transportTypesDTO) {
        TransportTypesDTO createdTransportTypesDTO = transportTypesService.save(transportTypesDTO);
        return ResponseEntity.ok(createdTransportTypesDTO);
    }

    @PutMapping("/transporttypes/{id}")
    public ResponseEntity<TransportTypesDTO> updateTransportType(@PathVariable int id, @RequestBody TransportTypesDTO transportTypesDTO) {
        TransportTypesDTO updatedTransportTypesDTO = transportTypesService.update(id, transportTypesDTO);
        return ResponseEntity.ok(updatedTransportTypesDTO);
    }

    @DeleteMapping("/transporttypes/{id}")
    public ResponseEntity<Void> deleteTransportType(@PathVariable int id) {
        transportTypesService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
