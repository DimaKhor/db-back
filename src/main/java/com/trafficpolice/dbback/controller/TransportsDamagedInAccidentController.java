package com.trafficpolice.dbback.controller;

import com.trafficpolice.dbback.dto.TransportsDamagedInAccidentDTO;
import com.trafficpolice.dbback.service.TransportsDamagedInAccidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/transports-damaged-in-accident")
    public ResponseEntity<TransportsDamagedInAccidentDTO> addTransportsDamagedInAccident(@RequestBody TransportsDamagedInAccidentDTO dto) {
        try {
            TransportsDamagedInAccidentDTO createdDto = service.addTransportsDamagedInAccident(dto);
            return ResponseEntity.ok(createdDto);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/transports-damaged-in-accident/{accidentId}/{transportId}")
    public ResponseEntity<TransportsDamagedInAccidentDTO> updateTransportsDamagedInAccident(@PathVariable int accidentId, @PathVariable int transportId, @RequestBody TransportsDamagedInAccidentDTO dto) {
        try {
            TransportsDamagedInAccidentDTO updatedDto = service.updateTransportsDamagedInAccident(accidentId, transportId, dto);
            return ResponseEntity.ok(updatedDto);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/transports-damaged-in-accident/{accidentId}/{transportId}")
    public ResponseEntity<Void> deleteTransportsDamagedInAccident(@PathVariable int accidentId, @PathVariable int transportId) {
        try {
            service.deleteTransportsDamagedInAccident(accidentId, transportId);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }
}
