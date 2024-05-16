package com.trafficpolice.dbback.controller;

import com.trafficpolice.dbback.dto.TransportsDamagedInAccidentDTO;
import com.trafficpolice.dbback.service.TransportsDamagedInAccidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TransportsDamagedInAccidentController {

    private final TransportsDamagedInAccidentService service;

    @GetMapping("/transports-damaged-in-accident/{accidentId}/{transportId}")
    public ResponseEntity<TransportsDamagedInAccidentDTO> getTransportsDamagedInAccidentById(@PathVariable int accidentId, @PathVariable int transportId) {
        try {
            TransportsDamagedInAccidentDTO dto = service.findById(accidentId, transportId);
            return ResponseEntity.ok(dto);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/transports-damaged-in-accident/by-accident/{accidentId}")
    public ResponseEntity<List<TransportsDamagedInAccidentDTO>> getTransportsDamagedInAccidentsByAccidentId(@PathVariable int accidentId) {
        try {
            List<TransportsDamagedInAccidentDTO> dtos = service.findByAccidentId(accidentId);
            return ResponseEntity.ok(dtos);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/transports-damaged-in-accident/by-transport/{transportId}")
    public ResponseEntity<List<TransportsDamagedInAccidentDTO>> getTransportsDamagedInAccidentsByTransportId(@PathVariable int transportId) {
        try {
            List<TransportsDamagedInAccidentDTO> dtos = service.findByTransportId(transportId);
            return ResponseEntity.ok(dtos);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/transports-damaged-in-accident")
    public ResponseEntity<List<TransportsDamagedInAccidentDTO>> getAllTransportsDamagedInAccidents() {
        List<TransportsDamagedInAccidentDTO> dtos = service.findAll();
        return ResponseEntity.ok(dtos);
    }
}
