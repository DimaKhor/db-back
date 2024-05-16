package com.trafficpolice.dbback.controller;

import com.trafficpolice.dbback.dto.TransportNumberDirectoryDTO;
import com.trafficpolice.dbback.service.TransportNumberDirectoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
