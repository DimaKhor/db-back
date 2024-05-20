package com.trafficpolice.dbback.controller;

import com.trafficpolice.dbback.dto.TransportsDamagedInAccidentDTO;
import com.trafficpolice.dbback.service.TransportsDamagedInAccidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TransportsDamagedInAccidentController {

    private final TransportsDamagedInAccidentService service;

    @GetMapping("/transports-damaged-in-accident/{accidentId}/{transportId}")
    public ResponseEntity<?> getTransportsDamagedInAccidentById(@PathVariable int accidentId, @PathVariable int transportId) {
        try {
            TransportsDamagedInAccidentDTO dto = service.findById(accidentId, transportId);
            return ResponseEntity.ok(dto);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error fetching data: Could not find transport with ID " + transportId + " for accident " + accidentId);
        }
    }

    @GetMapping("/transports-damaged-in-accident/by-accident/{accidentId}")
    public ResponseEntity<?> getTransportsDamagedInAccidentsByAccidentId(@PathVariable int accidentId) {
        try {
            List<TransportsDamagedInAccidentDTO> dtos = service.findByAccidentId(accidentId);
            return ResponseEntity.ok(dtos);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error fetching data: Could not find any transports for accident " + accidentId);
        }
    }

    @GetMapping("/transports-damaged-in-accident/by-transport/{transportId}")
    public ResponseEntity<?> getTransportsDamagedInAccidentsByTransportId(@PathVariable int transportId) {
        try {
            List<TransportsDamagedInAccidentDTO> dtos = service.findByTransportId(transportId);
            return ResponseEntity.ok(dtos);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error fetching data: Could not find any accidents for transport " + transportId);
        }
    }

    @GetMapping("/transports-damaged-in-accident")
    public ResponseEntity<?> getAllTransportsDamagedInAccidents() {
        try {
            List<TransportsDamagedInAccidentDTO> dtos = service.findAll();
            return ResponseEntity.ok(dtos);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching data: Could not retrieve transport damage records");
        }
    }

    @PostMapping("/transports-damaged-in-accident")
    public ResponseEntity<?> addTransportsDamagedInAccident(@RequestBody TransportsDamagedInAccidentDTO dto) {
        try {
            TransportsDamagedInAccidentDTO createdDto = service.addTransportsDamagedInAccident(dto);
            return ResponseEntity.ok(createdDto);
        } catch (RuntimeException e) {
            e.printStackTrace();
            String errorMessage = e.getMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }
    }

    @PutMapping("/transports-damaged-in-accident/{accidentId}/{transportId}")
    public ResponseEntity<?> updateTransportsDamagedInAccident(@PathVariable int accidentId, @PathVariable int transportId, @RequestBody TransportsDamagedInAccidentDTO dto) {
        try {
            service.updateTransportsDamagedInAccident(accidentId, transportId, dto);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error updating data: Could not update transport damage record with transport ID " + transportId + " for accident " + accidentId);
        }
    }

    @DeleteMapping("/transports-damaged-in-accident/{accidentId}/{transportId}")
    public ResponseEntity<?> deleteTransportsDamagedInAccident(@PathVariable int accidentId, @PathVariable int transportId) {
        try {
            service.deleteTransportsDamagedInAccident(accidentId, transportId);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error deleting data: Could not delete transport damage record with transport ID " + transportId + " for accident " + accidentId);
        }
    }
}
