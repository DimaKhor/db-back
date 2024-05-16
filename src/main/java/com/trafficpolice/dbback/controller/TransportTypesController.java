package com.trafficpolice.dbback.controller;

import com.trafficpolice.dbback.dto.TransportTypesDTO;
import com.trafficpolice.dbback.service.TransportTypesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
}
