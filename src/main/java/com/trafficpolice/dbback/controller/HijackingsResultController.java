package com.trafficpolice.dbback.controller;

import com.trafficpolice.dbback.dto.HijackingsResultDTO;
import com.trafficpolice.dbback.service.HijackingsResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HijackingsResultController {

    private final HijackingsResultService service;

    @GetMapping("/hijackings-result/{id}")
    public ResponseEntity<HijackingsResultDTO> getHijackingsResultById(@PathVariable int id) {
        try {
            HijackingsResultDTO dto = service.findById(id);
            return ResponseEntity.ok(dto);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/hijackings-result")
    public ResponseEntity<List<HijackingsResultDTO>> getAllHijackingsResults() {
        try {
            List<HijackingsResultDTO> dtos = service.findAll();
            return ResponseEntity.ok(dtos);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/hijackings-result")
    public ResponseEntity<?> createHijackingsResult(@RequestBody HijackingsResultDTO dto) {
        try {
            HijackingsResultDTO createdDTO = service.create(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdDTO);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Failed to create hijackings result: " + e.getMessage());
        }
    }

    @PutMapping("/hijackings-result/{id}")
    public ResponseEntity<?> updateHijackingsResult(@PathVariable int id, @RequestBody HijackingsResultDTO dto) {
        try {
            HijackingsResultDTO updatedDTO = service.update(id, dto);
            return ResponseEntity.ok(updatedDTO);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Failed to update hijackings result with id " + id + ": " + e.getMessage());
        }
    }

    @DeleteMapping("/hijackings-result/{id}")
    public ResponseEntity<?> deleteHijackingsResult(@PathVariable int id) {
        try {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Failed to delete hijackings result with id " + id + ": " + e.getMessage());
        }
    }
}
