package com.trafficpolice.dbback.controller;

import com.trafficpolice.dbback.dto.TransportNumberDirectoryDTO;
import com.trafficpolice.dbback.service.TransportNumberDirectoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TransportNumberDirectoryController {

    private final TransportNumberDirectoryService service;


    @GetMapping("/transport-number/{id}")
    public ResponseEntity<TransportNumberDirectoryDTO> getTransportNumberById(@PathVariable int id) {
        try {
            TransportNumberDirectoryDTO dto = service.findById(id);
            return ResponseEntity.ok(dto);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/transport-number")
    public ResponseEntity<List<TransportNumberDirectoryDTO>> getAllTransportNumbers() {
        try {
            List<TransportNumberDirectoryDTO> dtos = service.findAll();
            return ResponseEntity.ok(dtos);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/transport-number/by-number/{number}")
    public ResponseEntity<TransportNumberDirectoryDTO> getTransportNumberByNumber(@PathVariable String number) {
        try {
            TransportNumberDirectoryDTO dto = service.findByNumber(number);
            return ResponseEntity.ok(dto);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/transport-number/by-date-range")
    public ResponseEntity<List<TransportNumberDirectoryDTO>> getTransportNumbersByDateRange(
            @RequestParam("start_date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam("end_date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        try {
            List<TransportNumberDirectoryDTO> dtos = service.findByIssueDateBetween(startDate, endDate);
            return ResponseEntity.ok(dtos);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    //запрос 3
    @GetMapping("/transport-number/car-profile/{number}")
    public ResponseEntity<Object[]> getCarProfileByNumber(@PathVariable String number) {
        try {
            Object[] carProfile = service.getCarProfileByNumber(number);
            return ResponseEntity.ok(carProfile);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/transport-number")
    public ResponseEntity<?> createTransportNumber(@RequestBody TransportNumberDirectoryDTO dto) {
        try {
            TransportNumberDirectoryDTO createdDTO = service.create(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create transport number: " + e.getMessage());
        }
    }

    @PutMapping("/transport-number/{id}")
    public ResponseEntity<?> updateTransportNumber(@PathVariable int id, @RequestBody TransportNumberDirectoryDTO dto) {
        try {
            TransportNumberDirectoryDTO updatedDTO = service.update(id, dto);
            return ResponseEntity.ok(updatedDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update transport number with id " + id + ": " + e.getMessage());
        }
    }

    @DeleteMapping("/transport-number/{id}")
    public ResponseEntity<?> deleteTransportNumber(@PathVariable int id) {
        try {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete transport number with id " + id + ": " + e.getMessage());
        }
    }
}
