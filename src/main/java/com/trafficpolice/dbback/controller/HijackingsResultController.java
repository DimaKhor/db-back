package com.trafficpolice.dbback.controller;

import com.trafficpolice.dbback.dto.HijackingsResultDTO;
import com.trafficpolice.dbback.service.HijackingsResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
}
